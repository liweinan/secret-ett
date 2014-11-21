--------------------------------------
-- Pre-populate the Status packages --
--------------------------------------
insert into StatusPackage (id, version, name, tag) values (0, 0, 'Open', 'ORDINARY'), (1, 0, 'In Progress', 'IN_PROGRESS'), (2, 0, 'Done', 'FINISHED'), (3, 0, 'Pending / On Hold', 'ORDINARY'), (4, 0, 'Already Released', 'FINISHED'), (5, 0, 'Blocked', 'ORDINARY'), (6, 0, 'Need Upgrade', 'ORDINARY') ;

--------------------------------------------------
-- Used for testing purposes only, remove later --
--------------------------------------------------
insert into Workflow (id, version, name) values (0, 0, 'EAP');