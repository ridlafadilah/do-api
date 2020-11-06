--
-- Dumping data for table sec_role
--

INSERT INTO security.sec_role (role_uuid, role_name, description, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('06be80df-5c41-42a7-9050-b328312d2f3a', 'ROLE_ADMIN', 'Role Administrator', 0, true, '2018-12-02 20:52:28', NULL, NULL, NULL),
('d68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'ROLE_END', 'Role End User', 0, true, '2018-12-03 11:57:36', NULL, NULL, NULL);

--
-- Dumping data for table sec_user
-- admin | admin123
-- user | user123
-- male | user123
-- female | user123
--

INSERT INTO security.sec_user (user_uuid, username, password, account_enabled, account_non_expired, account_non_locked, credentials_non_expired, email, verification_code, raw, authority_default, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', 'admin', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'admin.dongkap@mailinator.com', NULL, NULL, 'ROLE_ADMIN', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', 'user', '$2a$13$SFGnAPHD/fP9BlNQU7drpeAKxFFgk9pDBIR5D4mb17RbNzKcU6K8S', true, true, true, true, 'user.dongkap@mailinator.com', NULL, NULL, 'ROLE_END', 0, true, '2018-12-05 14:50:55', NULL, NULL, NULL),
('e24b76e6-dee2-4262-80a1-f00f554d0440', 'male', '$2a$13$SFGnAPHD/fP9BlNQU7drpeAKxFFgk9pDBIR5D4mb17RbNzKcU6K8S', true, true, true, true, 'male.dongkap@mailinator.com', NULL, NULL, 'ROLE_END', 0, true, '2018-12-05 14:50:55', NULL, NULL, NULL),
('f2cc13e4-f865-4850-bf7c-8fc814e88915', 'female', '$2a$13$SFGnAPHD/fP9BlNQU7drpeAKxFFgk9pDBIR5D4mb17RbNzKcU6K8S', true, true, true, true, 'female.dongkap@mailinator.com', NULL, NULL, 'ROLE_END', 0, true, '2018-12-05 14:50:55', NULL, NULL, NULL);

--
-- Dumping data for table oauth_client_details
-- do-core | secretdo01
-- do-mobile | secretdo02
--

INSERT INTO security.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES
('do-core', 'profile,security,master,notification,general,file,report,workflow,finance', '$2a$13$qq7441xnpjEh.24xLE9xyeMZgRggZ7zeoFsXtfC6/NSoyfnJaWmZy', 'read,write,trust,check_token', 'password,refresh_token', '', 'ROLE_ADMIN,ROLE_END', 7200, 2592000, NULL, '1'),
('do-mobile', 'profile,security,master,notification,file,report,finance', '$2a$13$CrQeQDIECnBXzGm7QbnK1ulWrtVG7/82YL72U5SLjO.KZIk8kel9O', 'read,write,check_token', 'password,refresh_token', '', 'ROLE_END', 7200, 2592000, NULL, '1');

--
-- Dumping data for table sec_corporate
--

INSERT INTO security.sec_corporate(corporate_uuid, corporate_id, corporate_name, corporate_non_expired, email, address, telp_number) VALUES
('0ae4b095-d957-4ff1-a34d-7a440cc8d8ca', 'Dongkap', 'Dongkap', true, 'ridla.fadilah@gmail.com', 'Tangerang', '02175674774');

--
-- Dumping data for table sec_menu
--

INSERT INTO security.sec_menu (menu_uuid, code, url, "level", ordering, ordering_str, icon, is_leaf, is_home, is_group, "version", is_active, created_date, created_by, modified_date, modified_by, parent_uuid) VALUES
('b9029fd3-44cd-479c-965d-a8da1bfb20eb', '#DASHBOARD-PAGE', '/app/dashboard', 0, 0, '000', 'home-outline', false, true, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8', 'N/A', '#', 0, 1, '001', 'shield-outline', false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('4dc5f8f6-29e6-4cb1-a137-d4cdd3e2f634', '#MANAGEMENT-END-USER-PAGE', '/app/mgmt/user/end', 1, 0, '001.000', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8'),
('ab0c7ad2-5f53-4d42-9f49-e18a5d378c0f', '#MANAGEMENT-ADMIN-PAGE', '/app/mgmt/user/admin', 1, 1, '001.001', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8'),
('079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', '#MANAGEMENT-ROLE-PAGE', '/app/mgmt/role', 1, 2, '001.002', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8'),
('a695c3ec-71de-41d2-9623-fc96e55a6e35', '#MANAGEMENT-MENU-PAGE', '/app/mgmt/menu', 1, 3, '001.003', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8'),
('d59c50c6-4c88-40ab-ab32-127b8dcca3da', '#MANAGEMENT-FUNCTION-CONTROL-PAGE', '/app/mgmt/function/control', 1, 4, '001.004', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8'),
('3cf5bced-7def-426a-99f7-c8d44e1ce74c', 'N/A', '#', 0, 2, '002', 'settings-2-outline', false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('8c66c75e-5931-40af-af28-7c60b366ffd4', '#SYSCONF-PARAMETER-PAGE', '/app/sysconf/parameter', 1, 0, '002.000', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '3cf5bced-7def-426a-99f7-c8d44e1ce74c'),
('b81076bf-dda0-4278-b639-ba6f750eb8c9', '#SYSCONF-I18N-PAGE', '/app/sysconf/i18n', 1, 1, '002.001', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '3cf5bced-7def-426a-99f7-c8d44e1ce74c');
--- Extra Menu
INSERT INTO security.sec_menu (menu_uuid, code, url, "level", ordering, ordering_str, "type", icon, is_leaf, is_home, is_group, "version", is_active, created_date, created_by, modified_date, modified_by, parent_uuid) VALUES
('59e7e128-fe95-48d4-97a9-5441ae3d320c', '#PROFILE-PAGE', '/app/user/profile', 0, 0, '100', 'extra', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('bfad9391-0b0d-40c0-b823-321c9e5bd545', '#SYSTEM-PAGE', '/app/user/system', 0, 0, '100', 'extra', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', '#SECURITY-PAGE', '/app/user/security', 0, 1, '101', 'extra', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('1de67261-d2f9-4a01-bba2-68db9b3ad8c3', '#SETTINGS-PAGE', '/app/user/settings', 0, 2, '102', 'extra', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL);

--
-- Dumping data for table sec_menu_i18n
--

INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('2a44a77e-a7f0-4bec-a0a5-ae02a0a39ee9', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'en-US', 'Dashboard', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('de490bc6-c52e-4b9e-9157-e881b9203886', 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8', 'en-US', 'Permission Management', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('c229f514-2e2b-46c1-9daf-978b34ff732c', '4dc5f8f6-29e6-4cb1-a137-d4cdd3e2f634', 'en-US', 'User Management', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('60b2a62c-3aff-44e2-8bbd-aa13d64003f5', 'ab0c7ad2-5f53-4d42-9f49-e18a5d378c0f', 'en-US', 'Admin Management', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('504d4dd4-e718-4e1a-b509-1fd3b270b698', '079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', 'en-US', 'Role Management', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('ced677ee-7d07-4bff-bc0b-8ec334177812', 'a695c3ec-71de-41d2-9623-fc96e55a6e35', 'en-US', 'Menu Management', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('63fb8263-e672-41e7-ad42-ca3d1d61463c', 'd59c50c6-4c88-40ab-ab32-127b8dcca3da', 'en-US', 'Function Control', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('14306a74-a7ba-42f9-8bfe-600e95ffa3b3', '3cf5bced-7def-426a-99f7-c8d44e1ce74c', 'en-US', 'System Configuration', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('1afe5dbe-3519-452f-8060-615541116f0d', '8c66c75e-5931-40af-af28-7c60b366ffd4', 'en-US', 'Parameter', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('fb616657-b89d-4540-89bf-9a87ebf7ac83', 'b81076bf-dda0-4278-b639-ba6f750eb8c9', 'en-US', 'i18n', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);
--- Extra Menu
INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('21f9b5df-6e41-424f-98cf-e74850a19941', '59e7e128-fe95-48d4-97a9-5441ae3d320c', 'en-US', 'Profile', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('b431fb03-bf5c-49dd-b0fa-23d9a7adadbb', 'bfad9391-0b0d-40c0-b823-321c9e5bd545', 'en-US', 'System', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('3afc1a5c-b1f5-41e2-b54e-88c8565e0d99', '65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', 'en-US', 'Security', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('fb261b75-91ac-4d7a-ad79-bcc0cd35b33d', '1de67261-d2f9-4a01-bba2-68db9b3ad8c3', 'en-US', 'Settings', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);

INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('8edd8eaa-c665-4d81-ab41-49a61df686ba', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'id-ID', 'Beranda', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('e4d513bf-bebf-4167-bd46-062277f1b7ae', 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8', 'id-ID', 'Manajemen Hak Akses', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('d17d8cc6-78d0-4034-b1ec-48a9c9e1d1ae', '4dc5f8f6-29e6-4cb1-a137-d4cdd3e2f634', 'id-ID', 'Manajemen Pengguna', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('afd5dc05-19a7-418b-959d-2ef84c01237d', 'ab0c7ad2-5f53-4d42-9f49-e18a5d378c0f', 'id-ID', 'Manajemen Admin', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('dcb49994-1f47-4650-b869-8b318f93dde4', '079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', 'id-ID', 'Manajemen Peran', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('8076994f-f22b-4d9f-b093-a8ca7e1a5d11', 'a695c3ec-71de-41d2-9623-fc96e55a6e35', 'id-ID', 'Manajemen Menu', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('65b02457-088c-43fe-b219-4fb9cfeca97a', 'd59c50c6-4c88-40ab-ab32-127b8dcca3da', 'id-ID', 'Kontrol Fungsi', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('b104f88a-d41f-4941-8d67-3b1aa3ce6b17', '3cf5bced-7def-426a-99f7-c8d44e1ce74c', 'id-ID', 'Konfigurasi Sistem', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('4b6bf4dd-e8e5-48a3-8573-235659497c9f', '8c66c75e-5931-40af-af28-7c60b366ffd4', 'id-ID', 'Parameter', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('3dbb0fcb-0228-4f12-b3ad-27c3b5de5c0f', 'b81076bf-dda0-4278-b639-ba6f750eb8c9', 'id-ID', 'i18n', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);
--- Extra Menu
INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('1d78db6e-7352-4957-a13f-0f94e354a884', '59e7e128-fe95-48d4-97a9-5441ae3d320c', 'id-ID', 'Profil', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('85a3ad95-82df-41e0-880c-b5a74d6879e0', 'bfad9391-0b0d-40c0-b823-321c9e5bd545', 'id-ID', 'Sistem', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('6b0691fd-b496-4629-9987-c3c3afca53ec', '65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', 'id-ID', 'Keamanan', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('838cca1c-9873-4882-bebd-57e1d4dccaf2', '1de67261-d2f9-4a01-bba2-68db9b3ad8c3', 'id-ID', 'Pengaturan', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);

--
-- Dumping data for table sec_function
--

INSERT INTO security.sec_function(function_uuid, menu_uuid, role_uuid, access, version, is_active, created_date, created_by, modified_date, modified_by) VALUES
('f313c770-ae05-4664-b514-a471ed2ec577', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('d26385a1-5378-40a9-b0aa-d67eccd8557b', 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('18cef61e-6af9-4913-8216-424c0595aae7', '4dc5f8f6-29e6-4cb1-a137-d4cdd3e2f634', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('554fb325-19d3-4e95-ad32-530b2d8a5f83', 'ab0c7ad2-5f53-4d42-9f49-e18a5d378c0f', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('b9b4e0f0-0857-41b8-adb1-2a3fd235d13c', '079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('fe1ebd88-8702-416d-ad91-6bebf12302d8', 'a695c3ec-71de-41d2-9623-fc96e55a6e35', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('541ec496-62fe-4b01-93fb-363da7f3f6fc', 'd59c50c6-4c88-40ab-ab32-127b8dcca3da', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('d2e330b1-b87c-4891-ad95-ca01d95c9b16', '3cf5bced-7def-426a-99f7-c8d44e1ce74c', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('4803570a-360b-42b4-98da-f282acbbc1f2', '8c66c75e-5931-40af-af28-7c60b366ffd4', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('4254ad2f-7e80-4614-9ae7-7292397aaea1', 'b81076bf-dda0-4278-b639-ba6f750eb8c9', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null);
INSERT INTO security.sec_function(function_uuid, menu_uuid, role_uuid, access, version, is_active, created_date, created_by, modified_date, modified_by) VALUES
('6aca21b4-0f35-40c7-9882-f472d78c8185', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null);
--- Extra Menu
INSERT INTO security.sec_function(function_uuid, menu_uuid, role_uuid, access, version, is_active, created_date, created_by, modified_date, modified_by) VALUES
('e20c9233-d18b-46ee-b63f-238cf2d17af9', 'bfad9391-0b0d-40c0-b823-321c9e5bd545', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('e0a73e98-5f03-4ec2-a61d-0d4314732ab7', '65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('f05cb004-d570-4c95-a186-be95f6ae656d', '1de67261-d2f9-4a01-bba2-68db9b3ad8c3', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null);
INSERT INTO security.sec_function(function_uuid, menu_uuid, role_uuid, access, version, is_active, created_date, created_by, modified_date, modified_by) VALUES
('becbf470-6949-4820-a338-f0d9983e2f48', '59e7e128-fe95-48d4-97a9-5441ae3d320c', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('a881f8d4-0afb-4b11-953c-fab0141182e3', '65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('36b8f700-e4cf-45d2-aaf8-f1e3e7dbe836', '1de67261-d2f9-4a01-bba2-68db9b3ad8c3', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null);

--
-- Dumping data for table sec_r_user_corporate
--

INSERT INTO security.sec_r_user_corporate (user_uuid, corporate_uuid) VALUES
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('e24b76e6-dee2-4262-80a1-f00f554d0440', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('f2cc13e4-f865-4850-bf7c-8fc814e88915', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca');

--
-- Dumping data for table sec_r_user_role
--

INSERT INTO security.sec_r_user_role (user_uuid, role_uuid) VALUES
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', '06be80df-5c41-42a7-9050-b328312d2f3a'),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8'),
('e24b76e6-dee2-4262-80a1-f00f554d0440', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8'),
('f2cc13e4-f865-4850-bf7c-8fc814e88915', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8');

--
-- Dumping data for table sec_settings
--
	
INSERT INTO security.sec_settings (settings_uuid, user_uuid) VALUES 
('d2802cb0-cd2b-44bc-8a2c-431d1e0be597', '1ac29215-75e8-4e19-b4f1-e076da7ca1ab'),
('5fa21a4f-6231-4d25-9cdf-d7b1e25ff119', '38527ac6-edb6-4a4f-8e60-eede49c4c2a6'),
('e2dbaa84-5755-400e-9104-9013ea96d4bb', 'e24b76e6-dee2-4262-80a1-f00f554d0440'),
('cf35c2cf-c21b-46af-81cb-32d67d744128', 'f2cc13e4-f865-4850-bf7c-8fc814e88915');

--
-- Dumping data for table sec_contact_user
--
	
INSERT INTO security.sec_contact_user (contact_user_uuid, user_uuid, fullname, address, phone_number) VALUES 
('1af2403b-a4f8-4492-94c1-5d6ab8b4a094', '1ac29215-75e8-4e19-b4f1-e076da7ca1ab'	, 'Administrator', 'Tangerang', '02175674774'),
('d4fd659f-7bd6-4b1c-9127-e2dcf04651b0', '38527ac6-edb6-4a4f-8e60-eede49c4c2a6'	, 'End User', 'Jakarta', '02134567891'),
('b796e574-ac2d-4180-bf09-14dc146fd921', 'e24b76e6-dee2-4262-80a1-f00f554d0440'	, 'Male User', 'Bandung', '02134567892'),
('c4fa0235-0e4a-4145-aa3b-779a7ff369c2', 'f2cc13e4-f865-4850-bf7c-8fc814e88915'	, 'Female User', 'Surabaya', '02134567893');

--
-- Dumping data for table sec_personal_info
--
	
INSERT INTO security.sec_personal_info (personal_info_uuid, contact_user_uuid, id_number, gender, place_of_birth, date_of_birth) VALUES 
('2d341632-2fad-4394-a96f-6cfa54500dfc', 'd4fd659f-7bd6-4b1c-9127-e2dcf04651b0'	, '12345678901234561', 'GENDER.MALE', 'Jakarta', '01-01-1990'),
('863b16d2-943c-4a3c-a23a-00774aaaa82c', 'b796e574-ac2d-4180-bf09-14dc146fd921'	, '12345678901234562', 'GENDER.MALE', 'Bandung', '01-01-1990'),
('37a8d21a-5e30-46c5-a2be-3d73961d8471', 'c4fa0235-0e4a-4145-aa3b-779a7ff369c2'	, '12345678901234563', 'GENDER.FEMALE', 'Surabaya', '01-01-1990');