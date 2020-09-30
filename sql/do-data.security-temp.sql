--
-- Dumping data for table sec_menu
--

INSERT INTO security.sec_menu (menu_uuid, code, url, "level", ordering, ordering_str, icon, is_leaf, is_home, is_group, "version", is_active, created_date, created_by, modified_date, modified_by, parent_uuid) VALUES
('502bc59b-c2cd-4920-bfdf-6931354f02d0', '#EXERCISE-PAGE', '/app/exercise', 0, 999, '999', 'layout-outline', false, true, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL);

--
-- Dumping data for table sec_menu_i18n
--

INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('f80ef54f-5f9d-47a7-b356-834b451ba45e', '502bc59b-c2cd-4920-bfdf-6931354f02d0', 'en-US', 'Exercise', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);

INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('8972da69-186c-47ec-b5c1-75fc13c6d10b', '502bc59b-c2cd-4920-bfdf-6931354f02d0', 'id-ID', 'Latihan', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);

--
-- Dumping data for table sec_function
--

INSERT INTO security.sec_function(function_uuid, menu_uuid, role_uuid, access, version, is_active, created_date, created_by, modified_date, modified_by) VALUES
('9ddb452e-1dab-438b-855e-f735fc0c66a2', '502bc59b-c2cd-4920-bfdf-6931354f02d0', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'read,write,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null);
