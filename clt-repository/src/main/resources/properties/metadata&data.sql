#############################################################
# Insert the base metadata and data
# You have to replace the database before import to database
#############################################################

## Wechat Account
INSERT INTO

## Management ConnectApp
INSERT INTO `sandbox`.`connectapp` (`id`, `clientId`, `clientSecret`, `redirectURI`) VALUES ('891acad4-50d7-46d8-95ec-3d3171b734f6', 'top_m_management', '0000', '/pages/management.html');

## Management FunctionPermission
INSERT INTO `sandbox`.`functionpermission` (`id`, `url`, `name`) VALUES ('e6ea5a9a-1b19-4c66-bb0a-f53b31f1a48a', '/pages/management', 'management');
INSERT INTO `sandbox`.`functionpermission` (`id`, `url`, `name`) VALUES ('35eab190-d25a-4293-b6ec-434cad5688dd', '/pages/management', 'sfdc');
INSERT INTO `sandbox`.`functionpermission` (`id`, `url`, `name`) VALUES ('e4e64c75-1341-45d3-aab7-cb29f9302be1', '/pages/management', 'wechat');
INSERT INTO `sandbox`.`functionpermission` (`id`, `url`, `name`) VALUES ('9755f62b-25d5-4476-a951-1734164e30f2', '/pages/management', 'basic infor.');

## Management Scope
INSERT INTO `sandbox`.`scope` (`id`, `name`, `appId`, `functionPermissionId`) VALUES ('15269368-1954-4070-a6be-49b85c128743', 'Management', '891acad4-50d7-46d8-95ec-3d3171b734f6', 'e6ea5a9a-1b19-4c66-bb0a-f53b31f1a48a');
INSERT INTO `sandbox`.`scope` (`id`, `name`, `appId`, `functionPermissionId`) VALUES ('49d026f9-6638-414b-a976-bd5ad93c4933', 'Sfdc', '891acad4-50d7-46d8-95ec-3d3171b734f6', 'e6ea5a9a-1b19-4c66-bb0a-f53b31f1a48a');
INSERT INTO `sandbox`.`scope` (`id`, `name`, `appId`, `functionPermissionId`) VALUES ('f01091c2-aad5-448f-b780-7f5d7a23173c', 'Wechat', '891acad4-50d7-46d8-95ec-3d3171b734f6', 'e6ea5a9a-1b19-4c66-bb0a-f53b31f1a48a');
INSERT INTO `sandbox`.`scope` (`id`, `name`, `appId`, `functionPermissionId`) VALUES ('5502f760-42e5-4d55-a9b5-72920fdb9fa5', 'Base Information', '891acad4-50d7-46d8-95ec-3d3171b734f6', 'e6ea5a9a-1b19-4c66-bb0a-f53b31f1a48a');

## ObjectPermission
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('738f464c-7c9d-4d57-9622-98f038845e24', 'Account', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'Contact', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'Button', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('799bab25-2c5f-48eb-9f07-382ed795a582', 'ChatMessage', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('e7dbd17a-3d77-4759-b80e-7a35abf2fa95', 'ConnectApp', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('f5d7f05c-4e80-4269-8c18-901ffb36076a', 'FieldPermission', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'WechatUser', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('f60b34cf-74b2-4492-b092-151be31143b2', 'FunctionObject', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('7a3c51d8-419b-4963-85cc-21bcf608b090', 'FunctionPermission', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('8b6751d4-f1ba-4120-82fe-85910a86008a', 'LiveAgent', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('c1b8c6bf-7088-4cf6-88e6-9a13a98c29aa', 'ObjectPermission', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('36d52ff9-71ca-412d-8fd2-f17321b48311', 'Scope', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('8381ea2a-dd14-486c-b5ba-b633b86ec212', 'Sfdc', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('3c96f374-4563-4cf4-9f11-e74ecbe45647', 'UserApp', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'WechatAccount', '1', '1', '1', '1', '1');
INSERT INTO `sandbox`.`objectpermission` (`id`, `objectName`, `read`, `edit`, `del`, `add`, `Boolean`) VALUES ('7ae500d6-eba6-49e0-af7b-4f5d0e8bfa25', 'WechatTicket', '1', '1', '1', '1', '1');

## FunctionObject Relationship
INSERT INTO `sandbox`.`functionobject` (`id`, `functionPermissionId`, `objectPermissionId`) VALUES ('2fab4887-6dae-421a-9c90-9b2da53e174c', '35eab190-d25a-4293-b6ec-434cad5688dd', '8381ea2a-dd14-486c-b5ba-b633b86ec212');
INSERT INTO `sandbox`.`functionobject` (`id`, `functionPermissionId`, `objectPermissionId`) VALUES ('eb934708-9bf5-4c75-908a-7fa295360b35', '35eab190-d25a-4293-b6ec-434cad5688dd', '8b6751d4-f1ba-4120-82fe-85910a86008a');
INSERT INTO `sandbox`.`functionobject` (`id`, `functionPermissionId`, `objectPermissionId`) VALUES ('fd32f5b7-fa98-4430-b9a1-2f75fd9c5b9f', '35eab190-d25a-4293-b6ec-434cad5688dd', '5d151287-4b0c-4042-b4f5-b94bd8030d8a');
INSERT INTO `sandbox`.`functionobject` (`id`, `functionPermissionId`, `objectPermissionId`) VALUES ('fe1faf26-eab7-452d-811a-22fcbcf268d7', '9755f62b-25d5-4476-a951-1734164e30f2', '738f464c-7c9d-4d57-9622-98f038845e24');
INSERT INTO `sandbox`.`functionobject` (`id`, `functionPermissionId`, `objectPermissionId`) VALUES ('396570f2-05cd-4212-9f0d-0c1ba42af9f8', '9755f62b-25d5-4476-a951-1734164e30f2', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa');
INSERT INTO `sandbox`.`functionobject` (`id`, `functionPermissionId`, `objectPermissionId`) VALUES ('4f4ebf59-8c45-4db4-8320-5085fbd91e08', 'e4e64c75-1341-45d3-aab7-cb29f9302be1', '718909a0-4b26-44a1-ae04-d2be84bdc7f1');
INSERT INTO `sandbox`.`functionobject` (`id`, `functionPermissionId`, `objectPermissionId`) VALUES ('027989c3-f7ca-4067-8ae9-f85aa6b05b4a', 'e4e64c75-1341-45d3-aab7-cb29f9302be1', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446');

## Field Permission
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('5a27225a-a002-437d-81e3-98447307ffd2', '738f464c-7c9d-4d57-9622-98f038845e24', 'updateTime', '1', '1', '1', '1');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('a0abc94b-7d23-400b-ab7b-e67c5e26a259', '738f464c-7c9d-4d57-9622-98f038845e24', 'createTime', '1', '1', '1', '1');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('36564d88-43c3-4015-8f86-2043fdb0b8d9', '738f464c-7c9d-4d57-9622-98f038845e24', 'master', '1', '1', '1', '1');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('097cc043-85fa-42a2-a2e6-b38236b9a4c9', '738f464c-7c9d-4d57-9622-98f038845e24', 'name', '1', '1', '1', '1');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('3d24fa4f-1ef3-4235-9174-8c5c631b197a', '738f464c-7c9d-4d57-9622-98f038845e24', 'id', '1', '1', '1', '1');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('611406a4-8ba4-410f-a032-fde3d7d67c5e', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'id', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('52cd7c77-250e-43f1-9ed4-251dcc35af29', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'email', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('bbaf2611-5df4-4a03-9449-1418088e07e7', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'mobile', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('96b864d3-c0bc-411b-939e-04cbacaf3c0b', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'name', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('dbf31939-df66-4f2f-98d4-2ae45a39eff5', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'accountId', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('f8b856a9-e348-4240-94aa-f74f3a051c77', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'password', '0', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('f8f1ec43-45f1-432f-b13d-412a0b8db9ca', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'master', '0', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('c43d7420-2279-4690-84dc-41afe488dbbf', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'sessionId', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('845adbb9-95b8-4632-a8e3-dd8ddc42d1f6', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'openId', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('5b3fb2bb-0d1e-4904-ad2a-3fa088743303', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'createTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('e88c5336-30e8-45b4-bf35-104266e10df8', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'updateTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('5ede522a-d4cc-44a6-86af-a1c6618f9955', 'f92173ad-15c5-4f60-86ec-c0029ec6caaa', 'sessionUpdateTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('051f9701-ca13-4a4c-886f-999a8ea70d6d', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'id', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('fd893cd9-162f-4587-9bcc-f1001cd04931', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'openId', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('a53607bf-ad7f-42b9-9a8d-9383ce139c4e', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'bindTicket', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('6fc4317b-4297-44bc-b078-d248f6e4503f', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'nickname', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('ec4ac105-2bf9-4a4a-8d54-355b25db1b59', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'sex', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('6195be8b-f6e2-44c7-8104-54a451923954', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'language', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('b1710063-907b-448b-bd8c-6755c5619a29', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'city', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('5d363303-99d4-4cd2-93c9-8a3c83b75e05', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'province', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('f23cfc01-654e-45e1-bf8e-a087790835f4', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'country', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('f7faeffc-6530-4674-ad16-ed8eede78ec7', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'headimgurl', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('a61bab8c-85d0-473c-b67e-88eb81c8db9a', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'subscribeTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('5e5422c7-13be-484a-9f99-0606dc8dc176', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'unionid', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('d47de4e7-4e1d-45e9-bcb0-6e84edff0645', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'remark', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('a7804386-17c9-46a0-a468-a40d72b1ecda', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'wechatAccount', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('c30c473d-0ed7-4d25-bffb-345cd88e9171', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'contactId', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('93757549-d45e-4dcf-90e0-c39947624930', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'createTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('803ef59c-6f62-4457-a687-08e176a418c0', '8fd8d835-99cb-4d37-b5aa-e601b0ce3446', 'updateTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('173409fb-e119-4fa7-b4ef-65328de237f2', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'id', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('59b1cb56-c310-4106-b705-83f75887d8c4', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'wechatAccessToken', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('b513eb08-c9fb-4cc3-a85f-6f5ea67b6026', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'wechatAccount', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('b546a26c-33f7-4870-95db-14c5493d2ea0', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'wechatAppId', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('b3c4a282-bbca-4997-ba62-5363e7e752e1', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'wechatAppSecret', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('c74d3a12-5959-4112-8cbd-7eb3ba304a6e', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'wechatToken', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('be06a6cd-14d3-4c49-bab5-612f810fdcee', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'updateTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('752dc15c-1d11-4da4-aed7-39db8efa0251', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'createTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('8c3d8ccc-b8a2-4e4c-ae96-ae521d1e5e5c', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'firstTimeRefresh', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('8f17aaa4-90f5-48f6-862a-c81a2e52a7d3', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'refreshByUs', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('6e782c35-6ad0-4c47-88c3-06b6afe70d6d', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'accountId', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('04b2d19f-246a-444b-93a6-a640157755c1', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'laId', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('e06b5daf-f8d5-47a1-83f3-3dbed766ccf0', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'limitCount', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('0b3a3e9b-b244-4eac-a235-f121ef82b649', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'useDefault', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('057efead-6dec-4b2e-80eb-26e780820f54', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'name', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('d0dd3148-c9fd-44e9-a7a7-45cb38388377', '718909a0-4b26-44a1-ae04-d2be84bdc7f1', 'description', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('faab96fc-b73c-46c8-973b-0ee23e79ff89', '8381ea2a-dd14-486c-b5ba-b633b86ec212', 'id', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('951fadd1-95a0-4bad-a8ea-a3d96edc33ad', '8381ea2a-dd14-486c-b5ba-b633b86ec212', 'domain', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('df05a564-54fb-406c-95e5-7186c9baaf56', '8381ea2a-dd14-486c-b5ba-b633b86ec212', 'username', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('dc4c737e-5967-4e2d-a560-1dc43efbd8ed', '8381ea2a-dd14-486c-b5ba-b633b86ec212', 'password', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('fc4da362-f66a-43af-8bf1-66de29071c5a', '8381ea2a-dd14-486c-b5ba-b633b86ec212', 'securityToken', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('1996388a-75f4-4493-9fef-8c34a866be6f', '8381ea2a-dd14-486c-b5ba-b633b86ec212', 'description', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('ffb30ea3-199c-4bdf-ab5e-7a559e7454bf', '8381ea2a-dd14-486c-b5ba-b633b86ec212', 'package', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('659d1dc2-7bab-4bd8-a74e-78e23832c780', '8381ea2a-dd14-486c-b5ba-b633b86ec212', 'orgId', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('fff0a8a8-2de6-45a7-b35d-e427e2ec33a1', '8b6751d4-f1ba-4120-82fe-85910a86008a', 'id', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('5be569b8-cdda-483d-bd85-af111aff36bd', '8b6751d4-f1ba-4120-82fe-85910a86008a', 'deploymentId', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('3aaadffe-77fe-423c-966d-91fb5375b99c', '8b6751d4-f1ba-4120-82fe-85910a86008a', 'endPoint', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('0f98700c-9ee1-4fe0-ba26-b98c725078f7', '8b6751d4-f1ba-4120-82fe-85910a86008a', 'orgId', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('1ae900c9-9cb9-45ed-860e-415342813ab2', '8b6751d4-f1ba-4120-82fe-85910a86008a', 'updateTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('80e87361-e358-4b26-88a6-68dad0703eeb', '8b6751d4-f1ba-4120-82fe-85910a86008a', 'createTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('cb09e509-a6ff-4110-ad20-e312b55fe998', '8b6751d4-f1ba-4120-82fe-85910a86008a', 'accountId', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('418b87a8-b3e8-4b4e-ac14-0dfdf8fbbb2b', '5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'id', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('15cb601d-7641-49de-98e5-9f9cff864554', '5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'buttonId', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('fecc98a8-eadf-496a-bc08-77235f2d79d3', '5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'displayInfo', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('8f781ac0-c9eb-40c7-be69-c0768d2fc455', '5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'isDefault', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('f323fcdd-e14f-42cb-93be-e4adb11901c9', '5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'laId', '1', '1', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('53f9935a-ef32-403f-ad03-266db88526e1', '5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'limitCount', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('d020ba2a-ede1-48b3-9ce4-f81c9b55642e', '5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'createTime', '1', '0', '0', '0');
INSERT INTO `sandbox`.`fieldpermission` (`id`, `objectId`, `field`, `read`, `edit`, `del`, `add`) VALUES ('29bee27c-96df-49f2-97a2-c61c7712d294', '5d151287-4b0c-4042-b4f5-b94bd8030d8a', 'updateTime', '1', '0', '0', '0');
