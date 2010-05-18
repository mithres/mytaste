CREATE SEQUENCE hibseq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE hibseq OWNER TO root;

INSERT INTO resource (resourceid, resourcetype, resourcevalue) VALUES ('001', 0, '/**');
INSERT INTO resource (resourceid, resourcetype, resourcevalue) VALUES ('002', 0, '/home**');
INSERT INTO resource (resourceid, resourcetype, resourcevalue) VALUES ('003', 0, '/');
INSERT INTO resource (resourceid, resourcetype, resourcevalue) VALUES ('004', 0, '/vod/**');
INSERT INTO resource (resourceid, resourcetype, resourcevalue) VALUES ('005', 0, '/user/**');

INSERT INTO role (roleid, rolename) VALUES ('001', 'ROLE_ADMIN');
INSERT INTO role (roleid, rolename) VALUES ('002', 'ROLE_USER');
INSERT INTO role (roleid, rolename) VALUES ('003', 'ROLE_ANONYMOUS');

INSERT INTO role_resource (role_id, resource_id) VALUES ('001', '001');
INSERT INTO role_resource (role_id, resource_id) VALUES ('002', '002');
INSERT INTO role_resource (role_id, resource_id) VALUES ('001', '003');
INSERT INTO role_resource (role_id, resource_id) VALUES ('002', '003');
INSERT INTO role_resource (role_id, resource_id) VALUES ('003', '003');
INSERT INTO role_resource (role_id, resource_id) VALUES ('002', '004');
INSERT INTO role_resource (role_id, resource_id) VALUES ('002', '005');

INSERT INTO userinfo (username, accountbalance, email, enable, firstname, lastname, password, userindex, userlevel, uploadedavatar) VALUES ('admin', 999999, 'admin@gmail.com', true, 'FirstName', 'LastName', '96e79218965eb72c92a549dd5a330112', 0, 2, false);

INSERT INTO user_role (user_name, role_id) VALUES ('admin', '001');
INSERT INTO user_role (user_name, role_id) VALUES ('admin', '002');

