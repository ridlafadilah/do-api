--
-- Dumping data for table sec_role
--

INSERT INTO security.sec_role (role_uuid, role_name, description, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('06be80df-5c41-42a7-9050-b328312d2f3a', 'ROLE_ADMIN', 'Role Administrator', 0, true, '2018-12-02 20:52:28', NULL, NULL, NULL),
('9295fefc-7dfe-44ed-a4e3-52165cf44373', 'ROLE_ADMIN_REGIONAL', 'Role Admin Regional', 0, true, '2018-12-02 20:52:28', NULL, NULL, NULL),
('d68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'ROLE_END', 'Role End User', 0, true, '2018-12-03 11:57:36', NULL, NULL, NULL);

--
-- Dumping data for table sec_user
--

INSERT INTO security.sec_user (user_uuid, username, password, account_enabled, account_non_expired, account_non_locked, credentials_non_expired, email, verification_code, raw, authority_default, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', 'metro', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'metro.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('a695c3ec-71de-41d2-9623-fc96e55a6e35', 'aceh', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'aceh.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('d59c50c6-4c88-40ab-ab32-127b8dcca3da', 'sumut', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'sumut.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('504d4dd4-e718-4e1a-b509-1fd3b270b698', 'sumbar', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'sumbar.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('ced677ee-7d07-4bff-bc0b-8ec334177812', 'riau', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'riau.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('63fb8263-e672-41e7-ad42-ca3d1d61463c', 'kepri', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'kepri.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('dcb49994-1f47-4650-b869-8b318f93dde4', 'jambi', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'jambi.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('8076994f-f22b-4d9f-b093-a8ca7e1a5d11', 'bengkulu', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'bengkulu.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('65b02457-088c-43fe-b219-4fb9cfeca97a', 'sumsel', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'sumsel.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('b9b4e0f0-0857-41b8-adb1-2a3fd235d13c', 'babel', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'babel.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('fe1ebd88-8702-416d-ad91-6bebf12302d8', 'lampung', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'lampung.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('541ec496-62fe-4b01-93fb-363da7f3f6fc', 'banten', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'banten.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('becbf470-6949-4820-a338-f0d9983e2f48', 'jabar', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'jabar.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('a881f8d4-0afb-4b11-953c-fab0141182e3', 'jateng', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'jateng.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('36b8f700-e4cf-45d2-aaf8-f1e3e7dbe836', 'jogja', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'jogja.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('6aca21b4-0f35-40c7-9882-f472d78c8185', 'jatim', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'jatim.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('22cf3dab-a318-45d5-92dc-0ed73f4331bd', 'kalbar', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'kalbar.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('44c745e1-9de2-44f9-bb80-101a114ecf0a', 'kalteng', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'kalteng.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('6bee06f2-ef33-450f-b1bc-dd93a49559ca', 'kalsel', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'kalsel.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('502bc59b-c2cd-4920-bfdf-6931354f02d0', 'kaltim', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'kaltim.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('f80ef54f-5f9d-47a7-b356-834b451ba45e', 'kaltara', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'kaltara.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('8972da69-186c-47ec-b5c1-75fc13c6d10b', 'sulut', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'sulut.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('9ddb452e-1dab-438b-855e-f735fc0c66a2', 'gorontalo', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'gorontalo.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('cbfac2f3-7fdb-4e95-85b5-522188c46940', 'sulteng', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'sulteng.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('b424674e-f336-4bf5-8aa2-6dfbd2ad06a9', 'sulbar', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'sulbar.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('72be5b40-12b2-42e9-81e7-3ce4a6a6dc62', 'sulsel', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'sulsel.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('2cf708c7-3096-411b-869d-35b3db2d055e', 'sultra', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'sultra.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('d2ae598e-5ae7-4d76-82c2-6a7634062d37', 'bali', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'bali.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('18585be5-e7ec-4933-87b3-92384bf33f5f', 'ntb', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'ntb.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('0ec057fe-0640-440e-ac35-0ad9cb4acd13', 'ntt', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'ntt.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('7a7a006b-c40d-4917-af3a-cfa697a0b356', 'malut', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'malut.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('47882cf5-d6c5-4899-a746-3ca81f848e95', 'maluku', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'maluku.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('40c1b405-a157-405b-bc9b-f6c9a3218902', 'pabar', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'pabar.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('f93ecaa1-2dd4-4f34-abd1-37f2d3c395d2', 'papua', '$2a$13$iJoBglX2Rx/AkTc30eCDx.rHUfVyI3nkd3rCpyiCHXdnmqZmKm1Hm', true, true, true, true, 'papua.cer@mailinator.com', NULL, NULL, 'ROLE_ADMIN_REGIONAL', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', 'user', '$2a$13$SFGnAPHD/fP9BlNQU7drpeAKxFFgk9pDBIR5D4mb17RbNzKcU6K8S', true, true, true, true, 'user.cer@mailinator.com', NULL, NULL, 'ROLE_END', 0, true, '2018-12-05 14:50:55', NULL, NULL, NULL),
('e24b76e6-dee2-4262-80a1-f00f554d0440', 'male', '$2a$13$SFGnAPHD/fP9BlNQU7drpeAKxFFgk9pDBIR5D4mb17RbNzKcU6K8S', true, true, true, true, 'male.cer@mailinator.com', NULL, NULL, 'ROLE_END', 0, true, '2018-12-05 14:50:55', NULL, NULL, NULL),
('f2cc13e4-f865-4850-bf7c-8fc814e88915', 'female', '$2a$13$SFGnAPHD/fP9BlNQU7drpeAKxFFgk9pDBIR5D4mb17RbNzKcU6K8S', true, true, true, true, 'female.cer@mailinator.com', NULL, NULL, 'ROLE_END', 0, true, '2018-12-05 14:50:55', NULL, NULL, NULL);

--
-- Dumping data for table oauth_client_details
-- xa-core = secretxa01
-- xa-mobile = secretxa02
--

INSERT INTO security.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES
('xa-core', 'profile,security,master,notification,general,file,report,panic', '$2a$13$NrN1v1S4p0UsM8zDdvcUUeHqeUwmGbrobBaUwCO.eiIDVsIbIX5gy', 'read,write,trust,check_token', 'password,refresh_token', '', 'ROLE_ADMIN,ROLE_END', 7200, 3600, NULL, '1'),
('xa-mobile', 'profile,security,master,panic', '$2a$13$jqHw2BuGwJsZXf.eF3XVlOgGxkquOGx4vRDL6ll8UUyr0wEj03YLO', 'read,write,check_token', 'password,refresh_token', '', 'ROLE_END', 7200, 3600, NULL, '1');

--
-- Dumping data for table sec_corporate
--

INSERT INTO security.sec_corporate(corporate_uuid, corporate_id, corporate_name, corporate_non_expired, email, address, telp_number) VALUES
('0ae4b095-d957-4ff1-a34d-7a440cc8d8ca', 'CER', 'Civilians Emergency Report', true, 'admin.cer@mailinator.com', 'Jakarta', '+21 2221 9967');

--
-- Dumping data for table sec_menu
--

INSERT INTO security.sec_menu (menu_uuid, code, url, "level", ordering, ordering_str, icon, is_leaf, is_home, is_group, "version", is_active, created_date, created_by, modified_date, modified_by, parent_uuid) VALUES
('b9029fd3-44cd-479c-965d-a8da1bfb20eb', '#DASHBOARD-PAGE', '/app/dashboard', 0, 0, '000', 'home-outline', false, true, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('77687148-0cda-4a4a-96f1-900cd986c326', NULL, '#', 0, 1, '001', 'pie-chart-outline', false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', '#STATISTICS-AREA-PAGE', '/app/statistics/area', 1, 0, '001.000', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '77687148-0cda-4a4a-96f1-900cd986c326'),
('b2a03772-10d7-4ec5-bb68-99f2d6080cea', '#STATISTICS-GENDER-PAGE', '/app/statistics/gender', 1, 1, '001.001', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '77687148-0cda-4a4a-96f1-900cd986c326'),
('98a1bd05-2c42-47c4-afb7-9540f04e16b4', '#STATISTICS-PERIODE-PAGE', '/app/statistics/periode', 1, 2, '001.002', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '77687148-0cda-4a4a-96f1-900cd986c326'),
('5f4872f1-627e-4789-adf7-dc6db3884267', '#EMERGENCY-REPORTS-PAGE', '/app/reports/emergency', 0, 2, '002', 'alert-triangle-outline', false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('e76e6cfa-5ef2-4bc4-b5d4-dac5353d071c', '#FAKE-REPORTS-PAGE', '/app/reports/fake', 0, 3, '003', 'slash-outline', false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8', NULL, '#', 0, 4, '004', 'people-outline', false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('4dc5f8f6-29e6-4cb1-a137-d4cdd3e2f634', '#MANAGEMENT-CIVILIANS-PAGE', '/app/mgmt/user/civilians', 1, 0, '004.000', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8'),
('ab0c7ad2-5f53-4d42-9f49-e18a5d378c0f', '#MANAGEMENT-ADMIN-REGIONAL-PAGE', '/app/mgmt/user/admin-regional', 1, 1, '004.001', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8'),
('3cf5bced-7def-426a-99f7-c8d44e1ce74c', NULL, '#', 0, 5, '005', 'settings-2-outline', false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('8c66c75e-5931-40af-af28-7c60b366ffd4', '#SYSCONF-PARAMETER-PAGE', '/app/sysconf/parameter', 1, 0, '005.000', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '3cf5bced-7def-426a-99f7-c8d44e1ce74c'),
('b81076bf-dda0-4278-b639-ba6f750eb8c9', '#SYSCONF-LANGUAGE-PAGE', '/app/sysconf/language', 1, 1, '005.001', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '3cf5bced-7def-426a-99f7-c8d44e1ce74c');
--- Extra Menu
INSERT INTO security.sec_menu (menu_uuid, code, url, "level", ordering, ordering_str, "type", icon, is_leaf, is_home, is_group, "version", is_active, created_date, created_by, modified_date, modified_by, parent_uuid) VALUES
('59e7e128-fe95-48d4-97a9-5441ae3d320c', '#PROFILE-PAGE', '/app/user/profile', 0, 0, '100', 'extra', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', '#SECURITY-PAGE', '/app/user/security', 0, 1, '101', 'extra', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('1de67261-d2f9-4a01-bba2-68db9b3ad8c3', '#SETTINGS-PAGE', '/app/user/settings', 0, 2, '102', 'extra', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL);

--
-- Dumping data for table sec_menu_i18n
--

INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('2a44a77e-a7f0-4bec-a0a5-ae02a0a39ee9', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'en-US', 'Dashboard', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('28fa2833-f5aa-4eaf-a1f8-cec30efec97f', '77687148-0cda-4a4a-96f1-900cd986c326', 'en-US', 'Statistics', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('27a55e31-1543-4db2-aea2-383bad1f637e', '079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', 'en-US', 'Area', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('027efb44-9365-47e1-802c-98377029a5f5', 'b2a03772-10d7-4ec5-bb68-99f2d6080cea', 'en-US', 'Gender', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('39ac18b5-3ae7-40f1-99f2-02b5feb65041', '98a1bd05-2c42-47c4-afb7-9540f04e16b4', 'en-US', 'Periode', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('79ab6061-080d-4ed4-a5b2-dbf3c2aa2d28', '5f4872f1-627e-4789-adf7-dc6db3884267', 'en-US', 'Emergency Reports', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('92aefcd3-8556-4e6d-97a6-19005be74491', 'e76e6cfa-5ef2-4bc4-b5d4-dac5353d071c', 'en-US', 'Fake Reports', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('de490bc6-c52e-4b9e-9157-e881b9203886', 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8', 'en-US', 'User Management', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('c229f514-2e2b-46c1-9daf-978b34ff732c', '4dc5f8f6-29e6-4cb1-a137-d4cdd3e2f634', 'en-US', 'Civilians', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('60b2a62c-3aff-44e2-8bbd-aa13d64003f5', 'ab0c7ad2-5f53-4d42-9f49-e18a5d378c0f', 'en-US', 'Admin Regional', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('14306a74-a7ba-42f9-8bfe-600e95ffa3b3', '3cf5bced-7def-426a-99f7-c8d44e1ce74c', 'en-US', 'System Configuration', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('1afe5dbe-3519-452f-8060-615541116f0d', '8c66c75e-5931-40af-af28-7c60b366ffd4', 'en-US', 'Parameter', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('fb616657-b89d-4540-89bf-9a87ebf7ac83', 'b81076bf-dda0-4278-b639-ba6f750eb8c9', 'en-US', 'Language', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);
--- Extra Menu
INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('21f9b5df-6e41-424f-98cf-e74850a19941', '59e7e128-fe95-48d4-97a9-5441ae3d320c', 'en-US', 'Profile', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('3afc1a5c-b1f5-41e2-b54e-88c8565e0d99', '65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', 'en-US', 'Security', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('fb261b75-91ac-4d7a-ad79-bcc0cd35b33d', '1de67261-d2f9-4a01-bba2-68db9b3ad8c3', 'en-US', 'Settings', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);

INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('8edd8eaa-c665-4d81-ab41-49a61df686ba', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'id-ID', 'Beranda', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('e0498589-37f3-4bd2-8116-9f2713abe051', '77687148-0cda-4a4a-96f1-900cd986c326', 'id-ID', 'Statistik', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('a817dc35-1d8d-4d60-b52a-d823bd5f6b5b', '079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', 'id-ID', 'Area', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('77bf369b-fcf1-4eff-9241-d82e0dfc96a7', 'b2a03772-10d7-4ec5-bb68-99f2d6080cea', 'id-ID', 'Jenis Kelamin', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('fbd3e5f3-de06-46d8-80ae-5f2fb5a3cc8b', '98a1bd05-2c42-47c4-afb7-9540f04e16b4', 'id-ID', 'Periode', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('f143dd3b-ec59-401f-9796-c7c9d253db04', '5f4872f1-627e-4789-adf7-dc6db3884267', 'id-ID', 'Laporan Darurat', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('f6ea0727-8911-4e34-b1f7-26d50570f959', 'e76e6cfa-5ef2-4bc4-b5d4-dac5353d071c', 'id-ID', 'Laporan Fiktif', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('e4d513bf-bebf-4167-bd46-062277f1b7ae', 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8', 'id-ID', 'Manajemen Pengguna', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('d17d8cc6-78d0-4034-b1ec-48a9c9e1d1ae', '4dc5f8f6-29e6-4cb1-a137-d4cdd3e2f634', 'id-ID', 'Warga Sipil', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('afd5dc05-19a7-418b-959d-2ef84c01237d', 'ab0c7ad2-5f53-4d42-9f49-e18a5d378c0f', 'id-ID', 'Admin Regional', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('b104f88a-d41f-4941-8d67-3b1aa3ce6b17', '3cf5bced-7def-426a-99f7-c8d44e1ce74c', 'id-ID', 'Konfigurasi Sistem', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('4b6bf4dd-e8e5-48a3-8573-235659497c9f', '8c66c75e-5931-40af-af28-7c60b366ffd4', 'id-ID', 'Parameter', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('3dbb0fcb-0228-4f12-b3ad-27c3b5de5c0f', 'b81076bf-dda0-4278-b639-ba6f750eb8c9', 'id-ID', 'Bahasa', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);
--- Extra Menu
INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('1d78db6e-7352-4957-a13f-0f94e354a884', '59e7e128-fe95-48d4-97a9-5441ae3d320c', 'id-ID', 'Profil', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('6b0691fd-b496-4629-9987-c3c3afca53ec', '65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', 'id-ID', 'Keamanan', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('838cca1c-9873-4882-bebd-57e1d4dccaf2', '1de67261-d2f9-4a01-bba2-68db9b3ad8c3', 'id-ID', 'Pengaturan', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);

--
-- Dumping data for table sec_function
--

INSERT INTO security.sec_function(function_uuid, menu_uuid, role_uuid, access, version, is_active, created_date, created_by, modified_date, modified_by) VALUES
('f313c770-ae05-4664-b514-a471ed2ec577', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('22c3a77b-8f2d-4f04-a925-825a1e223a9e', '77687148-0cda-4a4a-96f1-900cd986c326', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('eee6d9d0-cc19-46e6-80ba-b9cb2360a76a', '079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('e731dcdf-89ba-465a-bfb4-f06a163f10ad', 'b2a03772-10d7-4ec5-bb68-99f2d6080cea', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('9f04d65e-8b90-484c-9e94-92f2256ffef1', '98a1bd05-2c42-47c4-afb7-9540f04e16b4', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('a723114c-ce92-41ac-bcbd-4b640c4dbc19', '5f4872f1-627e-4789-adf7-dc6db3884267', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('b137af7c-5720-4ed2-9305-32590dfdd82b', 'e76e6cfa-5ef2-4bc4-b5d4-dac5353d071c', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('d26385a1-5378-40a9-b0aa-d67eccd8557b', 'f6c6570b-0a29-4f3a-baa5-6d273c0bc2a8', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('18cef61e-6af9-4913-8216-424c0595aae7', '4dc5f8f6-29e6-4cb1-a137-d4cdd3e2f634', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('554fb325-19d3-4e95-ad32-530b2d8a5f83', 'ab0c7ad2-5f53-4d42-9f49-e18a5d378c0f', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('d2e330b1-b87c-4891-ad95-ca01d95c9b16', '3cf5bced-7def-426a-99f7-c8d44e1ce74c', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('4803570a-360b-42b4-98da-f282acbbc1f2', '8c66c75e-5931-40af-af28-7c60b366ffd4', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('4254ad2f-7e80-4614-9ae7-7292397aaea1', 'b81076bf-dda0-4278-b639-ba6f750eb8c9', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null);
--- Extra Menu
INSERT INTO security.sec_function(function_uuid, menu_uuid, role_uuid, access, version, is_active, created_date, created_by, modified_date, modified_by) VALUES
('e20c9233-d18b-46ee-b63f-238cf2d17af9', '59e7e128-fe95-48d4-97a9-5441ae3d320c', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('e0a73e98-5f03-4ec2-a61d-0d4314732ab7', '65691d1c-8f9d-4f4f-9223-c48f8c4dcee4', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('f05cb004-d570-4c95-a186-be95f6ae656d', '1de67261-d2f9-4a01-bba2-68db9b3ad8c3', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null);

--
-- Dumping data for table sec_r_user_corporate
--

INSERT INTO security.sec_r_user_corporate (user_uuid, corporate_uuid) VALUES
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('a695c3ec-71de-41d2-9623-fc96e55a6e35', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('d59c50c6-4c88-40ab-ab32-127b8dcca3da', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('504d4dd4-e718-4e1a-b509-1fd3b270b698', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('ced677ee-7d07-4bff-bc0b-8ec334177812', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('63fb8263-e672-41e7-ad42-ca3d1d61463c', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('dcb49994-1f47-4650-b869-8b318f93dde4', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('8076994f-f22b-4d9f-b093-a8ca7e1a5d11', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('65b02457-088c-43fe-b219-4fb9cfeca97a', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('b9b4e0f0-0857-41b8-adb1-2a3fd235d13c', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('fe1ebd88-8702-416d-ad91-6bebf12302d8', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('541ec496-62fe-4b01-93fb-363da7f3f6fc', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('becbf470-6949-4820-a338-f0d9983e2f48', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('a881f8d4-0afb-4b11-953c-fab0141182e3', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('36b8f700-e4cf-45d2-aaf8-f1e3e7dbe836', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('6aca21b4-0f35-40c7-9882-f472d78c8185', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('22cf3dab-a318-45d5-92dc-0ed73f4331bd', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('44c745e1-9de2-44f9-bb80-101a114ecf0a', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('6bee06f2-ef33-450f-b1bc-dd93a49559ca', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('502bc59b-c2cd-4920-bfdf-6931354f02d0', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('f80ef54f-5f9d-47a7-b356-834b451ba45e', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('8972da69-186c-47ec-b5c1-75fc13c6d10b', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('9ddb452e-1dab-438b-855e-f735fc0c66a2', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('cbfac2f3-7fdb-4e95-85b5-522188c46940', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('b424674e-f336-4bf5-8aa2-6dfbd2ad06a9', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('72be5b40-12b2-42e9-81e7-3ce4a6a6dc62', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('2cf708c7-3096-411b-869d-35b3db2d055e', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('d2ae598e-5ae7-4d76-82c2-6a7634062d37', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('18585be5-e7ec-4933-87b3-92384bf33f5f', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('0ec057fe-0640-440e-ac35-0ad9cb4acd13', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('7a7a006b-c40d-4917-af3a-cfa697a0b356', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('47882cf5-d6c5-4899-a746-3ca81f848e95', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('40c1b405-a157-405b-bc9b-f6c9a3218902', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('f93ecaa1-2dd4-4f34-abd1-37f2d3c395d2', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('e24b76e6-dee2-4262-80a1-f00f554d0440', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('f2cc13e4-f865-4850-bf7c-8fc814e88915', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca');

--
-- Dumping data for table sec_r_user_role
--

INSERT INTO security.sec_r_user_role (user_uuid, role_uuid) VALUES
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', '06be80df-5c41-42a7-9050-b328312d2f3a'),
('a695c3ec-71de-41d2-9623-fc96e55a6e35', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('d59c50c6-4c88-40ab-ab32-127b8dcca3da', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('504d4dd4-e718-4e1a-b509-1fd3b270b698', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('ced677ee-7d07-4bff-bc0b-8ec334177812', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('63fb8263-e672-41e7-ad42-ca3d1d61463c', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('dcb49994-1f47-4650-b869-8b318f93dde4', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('8076994f-f22b-4d9f-b093-a8ca7e1a5d11', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('65b02457-088c-43fe-b219-4fb9cfeca97a', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('b9b4e0f0-0857-41b8-adb1-2a3fd235d13c', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('fe1ebd88-8702-416d-ad91-6bebf12302d8', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('541ec496-62fe-4b01-93fb-363da7f3f6fc', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('becbf470-6949-4820-a338-f0d9983e2f48', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('a881f8d4-0afb-4b11-953c-fab0141182e3', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('36b8f700-e4cf-45d2-aaf8-f1e3e7dbe836', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('6aca21b4-0f35-40c7-9882-f472d78c8185', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('22cf3dab-a318-45d5-92dc-0ed73f4331bd', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('44c745e1-9de2-44f9-bb80-101a114ecf0a', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('6bee06f2-ef33-450f-b1bc-dd93a49559ca', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('502bc59b-c2cd-4920-bfdf-6931354f02d0', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('f80ef54f-5f9d-47a7-b356-834b451ba45e', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('8972da69-186c-47ec-b5c1-75fc13c6d10b', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('9ddb452e-1dab-438b-855e-f735fc0c66a2', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('cbfac2f3-7fdb-4e95-85b5-522188c46940', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('b424674e-f336-4bf5-8aa2-6dfbd2ad06a9', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('72be5b40-12b2-42e9-81e7-3ce4a6a6dc62', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('2cf708c7-3096-411b-869d-35b3db2d055e', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('d2ae598e-5ae7-4d76-82c2-6a7634062d37', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('18585be5-e7ec-4933-87b3-92384bf33f5f', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('0ec057fe-0640-440e-ac35-0ad9cb4acd13', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('7a7a006b-c40d-4917-af3a-cfa697a0b356', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('47882cf5-d6c5-4899-a746-3ca81f848e95', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('40c1b405-a157-405b-bc9b-f6c9a3218902', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('f93ecaa1-2dd4-4f34-abd1-37f2d3c395d2', '9295fefc-7dfe-44ed-a4e3-52165cf44373'),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8'),
('e24b76e6-dee2-4262-80a1-f00f554d0440', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8'),
('f2cc13e4-f865-4850-bf7c-8fc814e88915', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8');

--
-- Dumping data for table sec_settings
--
	
INSERT INTO security.sec_settings (settings_uuid, user_uuid) VALUES 
('d2802cb0-cd2b-44bc-8a2c-431d1e0be597', '1ac29215-75e8-4e19-b4f1-e076da7ca1ab'),
('34f62920-469e-497b-9e63-9ef332fafacc', 'a695c3ec-71de-41d2-9623-fc96e55a6e35'),
('c78363bb-1e54-48c8-af9f-55e70f5fc739', 'd59c50c6-4c88-40ab-ab32-127b8dcca3da'),
('90fd7ff5-c05a-4416-935c-cb41bb7c9d2a', '504d4dd4-e718-4e1a-b509-1fd3b270b698'),
('f0b7f6b0-73c2-49aa-bff1-06b1de7804d6', 'ced677ee-7d07-4bff-bc0b-8ec334177812'),
('40016679-05ef-4e98-a144-61ce5c24b6c2', '63fb8263-e672-41e7-ad42-ca3d1d61463c'),
('4d5c9d8b-a7b8-41a8-b541-d89043d1caab', 'dcb49994-1f47-4650-b869-8b318f93dde4'),
('dff279f7-0f20-4892-9678-2f1f0c6ff154', '8076994f-f22b-4d9f-b093-a8ca7e1a5d11'),
('5c744da3-d4af-439d-abed-9ca1d4aadc28', '65b02457-088c-43fe-b219-4fb9cfeca97a'),
('ef756def-5179-4e7c-9d5b-7add268483c7', 'b9b4e0f0-0857-41b8-adb1-2a3fd235d13c'),
('1da2dff2-8f22-42ee-9c61-8c84829694c5', 'fe1ebd88-8702-416d-ad91-6bebf12302d8'),
('539dc0fa-35e4-435e-9005-7f57d471927d', '541ec496-62fe-4b01-93fb-363da7f3f6fc'),
('99c7df8f-cddd-4dfa-a32f-b2e570c4d472', 'becbf470-6949-4820-a338-f0d9983e2f48'),
('ef1ba58b-5622-4ea7-8bdc-c60f941cf0bf', 'a881f8d4-0afb-4b11-953c-fab0141182e3'),
('90a64b1a-43ef-40cb-82a3-fd951eba864b', '36b8f700-e4cf-45d2-aaf8-f1e3e7dbe836'),
('826c08cc-ea48-4450-824b-925c69b9a75b', '6aca21b4-0f35-40c7-9882-f472d78c8185'),
('3ec8640a-b686-4f88-9ce0-3152e720c537', '22cf3dab-a318-45d5-92dc-0ed73f4331bd'),
('04e22595-3048-4cef-9f40-9212b588095e', '44c745e1-9de2-44f9-bb80-101a114ecf0a'),
('7f00c3d0-1d08-42a9-b051-9b2335f9c24f', '6bee06f2-ef33-450f-b1bc-dd93a49559ca'),
('14ae2eeb-eaee-4270-83c2-6f8f7dea9d10', '502bc59b-c2cd-4920-bfdf-6931354f02d0'),
('106dae81-e1fc-4ae9-a682-47bfb7111c53', 'f80ef54f-5f9d-47a7-b356-834b451ba45e'),
('a4dd457c-786e-4b40-b22d-4ed0bb9b45fb', '8972da69-186c-47ec-b5c1-75fc13c6d10b'),
('a1a18678-d859-49e6-a107-10e8830c41d3', '9ddb452e-1dab-438b-855e-f735fc0c66a2'),
('66605d59-1a2f-4d1d-9441-7d0b67d746d5', 'cbfac2f3-7fdb-4e95-85b5-522188c46940'),
('ffd4300f-9bd6-4e0d-b6e8-268242fb5692', 'b424674e-f336-4bf5-8aa2-6dfbd2ad06a9'),
('5ea86bc3-cbcf-4fd8-a339-9e123319bd2f', '72be5b40-12b2-42e9-81e7-3ce4a6a6dc62'),
('0a59b1dc-15c3-4eb0-8ac3-7a385fee5fe4', '2cf708c7-3096-411b-869d-35b3db2d055e'),
('498b0d1e-e603-4e65-8ef8-63697ad69aaa', 'd2ae598e-5ae7-4d76-82c2-6a7634062d37'),
('1cb92e66-6fd4-49f9-af91-51882a2e2a4d', '18585be5-e7ec-4933-87b3-92384bf33f5f'),
('d986a562-230b-497d-86b9-ec8c16c41584', '0ec057fe-0640-440e-ac35-0ad9cb4acd13'),
('c96eb7f2-87e2-4d1b-b00a-b38b5bf6c373', '7a7a006b-c40d-4917-af3a-cfa697a0b356'),
('3d2693e0-76cb-4403-86f6-504ccc1ab344', '47882cf5-d6c5-4899-a746-3ca81f848e95'),
('0acac38b-816e-445a-9e98-5f1a7f6ae45c', '40c1b405-a157-405b-bc9b-f6c9a3218902'),
('0df0ab59-cdf4-4765-b1a3-4dc9c2113a04', 'f93ecaa1-2dd4-4f34-abd1-37f2d3c395d2'),
('5fa21a4f-6231-4d25-9cdf-d7b1e25ff119', '38527ac6-edb6-4a4f-8e60-eede49c4c2a6'),
('e2dbaa84-5755-400e-9104-9013ea96d4bb', 'e24b76e6-dee2-4262-80a1-f00f554d0440'),
('cf35c2cf-c21b-46af-81cb-32d67d744128', 'f2cc13e4-f865-4850-bf7c-8fc814e88915');

--
-- Dumping data for table sec_contact_user
--
	
INSERT INTO security.sec_contact_user (contact_user_uuid, user_uuid, fullname, address, phone_number) VALUES 
('fa35f658-1e20-4509-ad3f-a8b532ea99c2', 'd2ae598e-5ae7-4d76-82c2-6a7634062d37'	, 'Polda Bali', 'Jl. WR Supratman No 7 Denpasar', '0361227174'),
('215410ba-763a-4ba4-8b4f-1cd9ce8321d1', 'b9b4e0f0-0857-41b8-adb1-2a3fd235d13c'	, 'Polda Bangka Belitung', 'Jl. Komplek Perkantoran Air Itam No 3 Pangkal Pinang', '0717437908'),
('86a62574-88ff-43d6-8e73-ea1c8551bf97', '541ec496-62fe-4b01-93fb-363da7f3f6fc'	, 'Polda Banten', 'Jl. Syeh Nawawi Al Bantani No 76 Serang', '0254228454'),
('46d26e4c-2694-4a96-87f6-007d33cc3dda', '8076994f-f22b-4d9f-b093-a8ca7e1a5d11'	, 'Polda Bengkulu', 'Jl. Adam Malik Km 9 Bengkulu', '073651041'),
('19ebb397-248a-419c-a869-994a728b41ff', '36b8f700-e4cf-45d2-aaf8-f1e3e7dbe836'	, 'Polda Daerah Istimewa Yogyakarta', 'Jl. Lingkar Utama Condong Catur Yogyakarta', '0274885009'),
('5816ca49-682e-41f2-8502-ef0237bb6410', '9ddb452e-1dab-438b-855e-f735fc0c66a2'	, 'Polda Gorontalo', 'Jl. Limboto Raya No 17 Gorontalo', '0435838536'),
('aedb367d-41c4-414f-aa92-9272de127662', 'dcb49994-1f47-4650-b869-8b318f93dde4'	, 'Polda Jambi', 'Jl. Jend Sudirman No 45 Jambi', '074122025'),
('f4a8d89f-0c81-40ed-8119-bdafbbd8b8a2', 'becbf470-6949-4820-a338-f0d9983e2f48'	, 'Polda Jawa Barat', 'Jl. Soekarno Hatta No 748 Bandung', '0227800005'),
('1e01c884-6cf7-4a78-99a4-0f909df7e1e8', 'a881f8d4-0afb-4b11-953c-fab0141182e3'	, 'Polda Jawa Tengah', 'Jl. Pahlawan No 1 Semarang', '0248413044'),
('9256a44a-adbc-45dd-84fb-5519353c4519', '6aca21b4-0f35-40c7-9882-f472d78c8185'	, 'Polda Jawa Timur', 'Jl. A. Yani No 116 Surabaya', '0318280333'),
('903ccd78-44de-4eab-bb4d-6f59366d9219', '22cf3dab-a318-45d5-92dc-0ed73f4331bd'	, 'Polda Kalimantan Barat', 'Jl. A. Yani No 1 Pontianak', '0561734004'),
('5ba04456-688d-4c57-9833-4bb69791a1ad', '6bee06f2-ef33-450f-b1bc-dd93a49559ca'	, 'Polda Kalimantan Selatan', 'Jl. S. Parman No 16 Banjarmasin', '05113354876'),
('654fcbe9-8349-4354-a0aa-aaafb582bcc2', '44c745e1-9de2-44f9-bb80-101a114ecf0a'	, 'Polda Kalimantan Tengah', 'Jl. Tjilik Riwut Km 1 Palangkaraya', '05363221720'),
('3ebb1012-eca2-4417-b1f2-7df301a930b1', '502bc59b-c2cd-4920-bfdf-6931354f02d0'	, 'Polda Kalimantan Timur', 'Jl. Syarifuddin Yoes No 99 Balikpapan', '0542421220'),
('e9f6ea0a-afdf-4ef8-bf77-b2b88bc79b08', 'f80ef54f-5f9d-47a7-b356-834b451ba45e'	, 'Polda Kalimantan Utara', 'Kalimantan Utara', '110'),
('813d39c2-3d53-449a-8ce3-44d55d5655b7', '63fb8263-e672-41e7-ad42-ca3d1d61463c'	, 'Polda Kepulauan Riau', 'Jl. Hang Jebat Batu Besar, Nongsa, Batam', '07787763541'),
('a7063322-0898-4476-a3ab-641ba5291b79', 'fe1ebd88-8702-416d-ad91-6bebf12302d8'	, 'Polda Lampung', 'Jl. WR Supratman No 1 Bandar Lampung', '0721486832'),
('ee97ec4a-fba2-4036-bb98-5aeb7e46192b', '47882cf5-d6c5-4899-a746-3ca81f848e95'	, 'Polda Maluku', 'Jl. Rijali No 1 Ambon', '0911352912'),
('d667c6ba-9bef-40fd-9a8a-274bdf51a655', '7a7a006b-c40d-4917-af3a-cfa697a0b356'	, 'Polda Maluku Utara', 'Jl. Kapitan Pattimura, Kalumpang, Ternate', '0921327045'),
('1af2403b-a4f8-4492-94c1-5d6ab8b4a094', '1ac29215-75e8-4e19-b4f1-e076da7ca1ab'	, 'Polda Metro Jakarta Raya', 'Jl. Jend Sudirman No 55 Jakarta Selatan', '0215234001'),
('d9c14d4e-890b-49c2-aa15-d70ab87e0c42', 'a695c3ec-71de-41d2-9623-fc96e55a6e35'	, 'Polda Nanggroe Aceh Darussalam', 'Jl. Cut Meutia No 25 Banda Aceh', '065129556'),
('f9cdb922-32bb-45f7-bb5a-5b853d4e1feb', '18585be5-e7ec-4933-87b3-92384bf33f5f'	, 'Polda Nusa Tenggara Barat', 'Jl. Langko No 77 Mataram', '0370633152'),
('5660a4b4-13a4-4d0c-9bdd-0afebd97f448', '0ec057fe-0640-440e-ac35-0ad9cb4acd13'	, 'Polda Nusa Tenggara Timur', 'Jl. Soeharto No 3 Kupang', '0380833132'),
('25cc4e94-7c55-479c-be57-421f6538fd42', 'f93ecaa1-2dd4-4f34-abd1-37f2d3c395d2'	, 'Polda Papua', 'Jl. Dr Samratulangi No 8 Jayapura', '0967531014'),
('46cb004a-9025-4685-b8fa-e082dd5d7a62', '40c1b405-a157-405b-bc9b-f6c9a3218902'	, 'Polda Papua Barat', 'JL. Pahlawan Sanggeng', '0986211253'),
('3ba19d4f-fc1e-41b0-9402-5ac3eb0cf02c', 'ced677ee-7d07-4bff-bc0b-8ec334177812'	, 'Polda Riau', 'Jl. Jend Sudirman No 235 Pekanbaru', '076131307'),
('94559706-2634-4674-a19b-cbb4858f08ca', '72be5b40-12b2-42e9-81e7-3ce4a6a6dc62'	, 'Polda Sulawesi Selatan', 'Jl. Perintis Kemerdekaan Km 16 Makasar', '0411515101'),
('b8908d8f-cedf-426c-960b-f9111fdcaed1', 'cbfac2f3-7fdb-4e95-85b5-522188c46940'	, 'Polda Sulawesi Tengah', 'Jl. Dr Samratulangi No 78 Palu', '0451421555'),
('5ca6737e-1e09-43f8-85c3-2399d488a82b', '2cf708c7-3096-411b-869d-35b3db2d055e'	, 'Polda Sulawesi Tenggara', 'Jl. Haluleo No 1 Kendari', '0401391555'),
('e34bd0c3-3291-4c5e-abd9-1712788f9911', '8972da69-186c-47ec-b5c1-75fc13c6d10b'	, 'Polda Sulawesi Utara', 'Jl. Bethesda No 62 Manado', '0431862019'),
('0ed39e70-9d22-4848-9916-92ccdc76a6e8', 'b424674e-f336-4bf5-8aa2-6dfbd2ad06a9'	, 'Polda Sulawesi Barat', 'Jl. Aiptu Nurman No. 1, Kalubibing Mamuyu', '110'),
('85a3ad95-82df-41e0-880c-b5a74d6879e0', '504d4dd4-e718-4e1a-b509-1fd3b270b698'	, 'Polda Sumatera Barat', 'Jl. Sudirman No 55 Padang', '075133416'),
('b431fb03-bf5c-49dd-b0fa-23d9a7adadbb', '65b02457-088c-43fe-b219-4fb9cfeca97a'	, 'Polda Sumatera Selatan', 'Jl. Jend Sudirman Km 4,5 Palembang', '0711320550'),
('bfad9391-0b0d-40c0-b823-321c9e5bd545', 'd59c50c6-4c88-40ab-ab32-127b8dcca3da'	, 'Polda Sumatera Utara', 'Jl. SM Raja XII Km 10,5 No 60 Medan', '0617879363'),
('d4fd659f-7bd6-4b1c-9127-e2dcf04651b0', '38527ac6-edb6-4a4f-8e60-eede49c4c2a6'	, 'User 01', 'Tangerang', '02134567891'),
('b796e574-ac2d-4180-bf09-14dc146fd921', 'e24b76e6-dee2-4262-80a1-f00f554d0440'	, 'User Male', 'Bandung', '02134567892'),
('c4fa0235-0e4a-4145-aa3b-779a7ff369c2', 'f2cc13e4-f865-4850-bf7c-8fc814e88915'	, 'User Female', 'Surabaya', '02134567893');

--
-- Dumping data for table sec_personal_info
--
	
INSERT INTO security.sec_personal_info (personal_info_uuid, contact_user_uuid, id_number, gender, place_of_birth, date_of_birth) VALUES 
('2d341632-2fad-4394-a96f-6cfa54500dfc', 'd4fd659f-7bd6-4b1c-9127-e2dcf04651b0'	, '12345678901234561', 'GENDER.MALE', 'Tangerang', '01-01-1990'),
('863b16d2-943c-4a3c-a23a-00774aaaa82c', 'b796e574-ac2d-4180-bf09-14dc146fd921'	, '12345678901234562', 'GENDER.MALE', 'Bandung', '01-01-1990'),
('37a8d21a-5e30-46c5-a2be-3d73961d8471', 'c4fa0235-0e4a-4145-aa3b-779a7ff369c2'	, '12345678901234563', 'GENDER.FEMALE', 'Surabaya', '01-01-1990');


--
-- Dumping data for table sec_personal_support
--
	
INSERT INTO security.sec_personal_support (personal_support_uuid, personal_info_uuid, reference_name, reference_address, reference_phone_number, relationship) VALUES 
('189913b7-ac93-458d-8d98-5e6212bc1c05', '2d341632-2fad-4394-a96f-6cfa54500dfc'	, 'User Reference', 'Jakarta', '02134567890', 'RELATIONSHIP.OTHERS'),
('919f9504-e661-40ce-8021-3df63e59b011', '863b16d2-943c-4a3c-a23a-00774aaaa82c'	, 'User Reference Male', 'Tasik', '02134567891', 'RELATIONSHIP.SIBLING'),
('8a8f7a93-c84d-497d-bed3-e04dd4600bbb', '37a8d21a-5e30-46c5-a2be-3d73961d8471'	, 'User Reference Female', 'Pasuruan', '02134567892', 'RELATIONSHIP.COUPLE');