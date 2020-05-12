package com.dongkap.file.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.common.utils.FileSizeUnit;
import com.dongkap.feign.dto.file.FileMetadataDto;

@Component
public class FileUtils {
	
	private ZipInputStream zis;

	public void write(String path, String fileName, byte[] fileContent) throws Exception {
		new File(path).mkdir();
		RandomAccessFile stream = new RandomAccessFile(new File(path, fileName), "rw");
		FileChannel channel = stream.getChannel();
		FileLock lock = null;
		try {
			lock = channel.tryLock();
			ByteBuffer buffer = ByteBuffer.allocate(fileContent.length);
			buffer.put(fileContent);
			buffer.flip();
			channel.write(buffer);
			lock.release();
		} catch (final OverlappingFileLockException e) {
			stream.close();
			channel.close();
		}
		stream.close();
		channel.close();
	}

	public String writeFile(String filePath, byte[] fileContent) throws Exception {
		String checksum = fileChecksum("MD5", fileContent);
		filePath = getFilePathString(checksum, filePath, false);
		write(filePath, checksum, fileContent);
		return filePath;
	}

	public FileMetadataDto writeFile(String filePath, String filename, byte[] fileContent) throws Exception {
		String checksum = fileChecksum("MD5", fileContent); 
		String fileFullPath = getFilePathString(checksum, filePath, false);
		write(filePath, checksum, fileContent);
		FileChannel fileChannel = FileChannel.open(Paths.get(fileFullPath));
		long fileSize = fileChannel.size();
		FileMetadataDto fileMetadata = new FileMetadataDto();
		fileMetadata.setChecksum(checksum);
		fileMetadata.setFullPath(fileFullPath);
		fileMetadata.setLocation(filePath);
		fileMetadata.setFullname(filename);
		fileMetadata.setShortname(getFilenameWithoutExtension(filename));
		fileMetadata.setExtension(fileExtension(filename));
		fileMetadata.setSize(getSize(fileSize, FileSizeUnit.bytes));
		fileMetadata.setFileDate(new Date());
		fileMetadata.setFileType(filename == null ? "application/octet-stream" : new MimetypesFileTypeMap().getContentType(filename));
		return fileMetadata;
	}

	public FileMetadataDto writeFile(String filePath, MultipartFile multipart) throws Exception {
		String checksum = fileChecksum("MD5", multipart.getBytes()); 
		String fileFullPath = getFilePathString(checksum, filePath, false);
		write(filePath, checksum, multipart.getBytes());
		FileMetadataDto fileMetadata = new FileMetadataDto();
		fileMetadata.setChecksum(checksum);
		fileMetadata.setFullPath(fileFullPath);
		fileMetadata.setLocation(filePath);
		fileMetadata.setFullname(multipart.getOriginalFilename());
		fileMetadata.setShortname(getFilenameWithoutExtension(multipart.getOriginalFilename()));
		fileMetadata.setExtension(fileExtension(multipart.getOriginalFilename()));
		fileMetadata.setSize(getSize(multipart, FileSizeUnit.bytes));
		fileMetadata.setFileDate(new Date());
		fileMetadata.setFileType(multipart.getOriginalFilename() == null ? "application/octet-stream" : new MimetypesFileTypeMap().getContentType(multipart.getOriginalFilename()));
		return fileMetadata;
	}

	public void zip(List<File> files, String zipLocation) throws IOException {
		FileOutputStream fos = new FileOutputStream(zipLocation);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		files.forEach(file->{
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				ZipEntry zipEntry = new ZipEntry(file.getName());
				zipOut.putNextEntry(zipEntry);
				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zipOut.write(bytes, 0, length);
				}
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		zipOut.close();
		fos.close();
	}
	
	public List<File> unzip(ZipInputStream zis, String unzipLocation) throws IOException {
		byte[] buffer = new byte[1024];
		ZipEntry zipEntry = zis.getNextEntry();
		List<File> files = new ArrayList<File>();
		while (zipEntry != null) {
			String fileName = zipEntry.getName();
			File newFile = new File(unzipLocation + fileName);
			FileOutputStream fos = new FileOutputStream(newFile);
			int len;
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			files.add(newFile);
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
		return files;
	}
	
	public List<FileMetadataDto> extract(String filePath, MultipartFile multipart) throws Exception {
		String zipFullPath = filePath+"/"+multipart.getOriginalFilename();
		write(filePath, multipart.getOriginalFilename(), multipart.getBytes());
		zis = new ZipInputStream(new FileInputStream(zipFullPath));
		ZipEntry zipEntry = null;
		List<FileMetadataDto> fileMetadatas = new ArrayList<FileMetadataDto>();
		ByteArrayOutputStream out = null;
		while ((zipEntry = zis.getNextEntry()) != null) {
			if(zipEntry.isDirectory())
				throw new SystemErrorException(ErrorCode.ERR_SYS0502);
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = zis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
			String fileName = zipEntry.getName();
			String checksum = fileChecksum("MD5", out.toByteArray());
			String fileFullPath = getFilePathString(checksum, filePath, false);
			write(filePath, checksum, out.toByteArray());
			FileChannel fileChannel = FileChannel.open(Paths.get(fileFullPath));
			long fileSize = fileChannel.size();
			FileMetadataDto fileMetadata = new FileMetadataDto();
			fileMetadata.setChecksum(checksum);
			fileMetadata.setFullPath(fileFullPath);
			fileMetadata.setLocation(filePath);
			fileMetadata.setFullname(fileName);
			fileMetadata.setShortname(getFilenameWithoutExtension(fileName));
			fileMetadata.setExtension(fileExtension(fileName));
			fileMetadata.setSize(getSize(fileSize, FileSizeUnit.bytes));
			fileMetadata.setFileDate(new Date());
			fileMetadata.setFileType(fileName == null ? "application/octet-stream" : new MimetypesFileTypeMap().getContentType(fileName));
			fileMetadatas.add(fileMetadata);
            out.close();
		}
		zis.closeEntry();
		zis.close();
		new File(zipFullPath).delete();
		return fileMetadatas;
	}

	public static String fileChecksum(String hashAlgorithm, byte[] bytes) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm);
		messageDigest.update(bytes, 0, bytes.length);
		byte[] mdBytes = messageDigest.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mdBytes.length; i++) {
			sb.append(Integer.toString((mdBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public static String getFilenameWithoutExtension(String fileName) {
		if(fileName.lastIndexOf(".") < 0)
			return null;
		String fn = fileName.substring(0, fileName.lastIndexOf("."));
		return fn;
	}

	public static String fileType(File file) throws IOException {
		return Files.probeContentType(file.toPath());
	}

	public static String fileExtension(File file) throws IOException {
		return FilenameUtils.getExtension(file.getName());
	}
	
	public static String fileExtension(String fileName) {
		if(fileName.lastIndexOf(".") < 0)
			return null;
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		return fileExtension;
	}

	public BigDecimal getSize(MultipartFile file, FileSizeUnit sizeUnit) {
		long bytes = file.getSize();
		double kilobytes = (bytes / 1024);
		double megabytes = (kilobytes / 1024);
		double gigabytes = (megabytes / 1024);
		double terabytes = (gigabytes / 1024);
		double petabytes = (terabytes / 1024);
		double exabytes = (petabytes / 1024);
		double zettabytes = (exabytes / 1024);
		double yottabytes = (zettabytes / 1024);
		BigDecimal size = BigDecimal.ZERO;
		if (sizeUnit.equals(FileSizeUnit.bytes)) {
			size = BigDecimal.valueOf(bytes);
		} else if (sizeUnit.equals(FileSizeUnit.kilobytes)) {
			size = BigDecimal.valueOf(kilobytes);
		} else if (sizeUnit.equals(FileSizeUnit.megabytes)) {
			size = BigDecimal.valueOf(megabytes);
		} else if (sizeUnit.equals(FileSizeUnit.gigabytes)) {
			size = BigDecimal.valueOf(gigabytes);
		} else if (sizeUnit.equals(FileSizeUnit.terabytes)) {
			size = BigDecimal.valueOf(terabytes);
		} else if (sizeUnit.equals(FileSizeUnit.petabytes)) {
			size = BigDecimal.valueOf(petabytes);
		} else if (sizeUnit.equals(FileSizeUnit.exabytes)) {
			size = BigDecimal.valueOf(exabytes);
		} else if (sizeUnit.equals(FileSizeUnit.zettabytes)) {
			size = BigDecimal.valueOf(zettabytes);
		} else if (sizeUnit.equals(FileSizeUnit.yottabytes)) {
			size = BigDecimal.valueOf(yottabytes);
		}
		return size;
	}

	public BigDecimal getSize(File file, FileSizeUnit sizeUnit) {
		long bytes = file.length();
		double kilobytes = (bytes / 1024);
		double megabytes = (kilobytes / 1024);
		double gigabytes = (megabytes / 1024);
		double terabytes = (gigabytes / 1024);
		double petabytes = (terabytes / 1024);
		double exabytes = (petabytes / 1024);
		double zettabytes = (exabytes / 1024);
		double yottabytes = (zettabytes / 1024);
		BigDecimal size = BigDecimal.ZERO;
		if (sizeUnit.equals(FileSizeUnit.bytes)) {
			size = BigDecimal.valueOf(bytes);
		} else if (sizeUnit.equals(FileSizeUnit.kilobytes)) {
			size = BigDecimal.valueOf(kilobytes);
		} else if (sizeUnit.equals(FileSizeUnit.megabytes)) {
			size = BigDecimal.valueOf(megabytes);
		} else if (sizeUnit.equals(FileSizeUnit.gigabytes)) {
			size = BigDecimal.valueOf(gigabytes);
		} else if (sizeUnit.equals(FileSizeUnit.terabytes)) {
			size = BigDecimal.valueOf(terabytes);
		} else if (sizeUnit.equals(FileSizeUnit.petabytes)) {
			size = BigDecimal.valueOf(petabytes);
		} else if (sizeUnit.equals(FileSizeUnit.exabytes)) {
			size = BigDecimal.valueOf(exabytes);
		} else if (sizeUnit.equals(FileSizeUnit.zettabytes)) {
			size = BigDecimal.valueOf(zettabytes);
		} else if (sizeUnit.equals(FileSizeUnit.yottabytes)) {
			size = BigDecimal.valueOf(yottabytes);
		}
		return size;
	}

	public BigDecimal getSize(long bytes, FileSizeUnit sizeUnit) {
		double kilobytes = (bytes / 1024);
		double megabytes = (kilobytes / 1024);
		double gigabytes = (megabytes / 1024);
		double terabytes = (gigabytes / 1024);
		double petabytes = (terabytes / 1024);
		double exabytes = (petabytes / 1024);
		double zettabytes = (exabytes / 1024);
		double yottabytes = (zettabytes / 1024);
		BigDecimal size = BigDecimal.ZERO;
		if (sizeUnit.equals(FileSizeUnit.bytes)) {
			size = BigDecimal.valueOf(bytes);
		} else if (sizeUnit.equals(FileSizeUnit.kilobytes)) {
			size = BigDecimal.valueOf(kilobytes);
		} else if (sizeUnit.equals(FileSizeUnit.megabytes)) {
			size = BigDecimal.valueOf(megabytes);
		} else if (sizeUnit.equals(FileSizeUnit.gigabytes)) {
			size = BigDecimal.valueOf(gigabytes);
		} else if (sizeUnit.equals(FileSizeUnit.terabytes)) {
			size = BigDecimal.valueOf(terabytes);
		} else if (sizeUnit.equals(FileSizeUnit.petabytes)) {
			size = BigDecimal.valueOf(petabytes);
		} else if (sizeUnit.equals(FileSizeUnit.exabytes)) {
			size = BigDecimal.valueOf(exabytes);
		} else if (sizeUnit.equals(FileSizeUnit.zettabytes)) {
			size = BigDecimal.valueOf(zettabytes);
		} else if (sizeUnit.equals(FileSizeUnit.yottabytes)) {
			size = BigDecimal.valueOf(yottabytes);
		}
		return size;
	}
	
	public String getFilePathString(String filename, String fileLocation, boolean appendCurrentTimeMillis) {
		StringBuilder sb = new StringBuilder();
		sb.append(fileLocation);
		sb.append(File.separator);
		if (appendCurrentTimeMillis) {
			sb.append(System.currentTimeMillis());
		}
		sb.append(filename);
		return sb.toString();
	}

}
