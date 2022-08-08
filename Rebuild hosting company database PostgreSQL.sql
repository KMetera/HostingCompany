-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-06-19 11:17:30.978

-- foreign keys
ALTER TABLE Employee
    DROP CONSTRAINT Employee_Team_Member;

ALTER TABLE Employee
    DROP CONSTRAINT Employee_Team_Supervisor;

ALTER TABLE Payment
    DROP CONSTRAINT Payment_Service;

ALTER TABLE Payment
    DROP CONSTRAINT Payment_Subscriber;

ALTER TABLE Report
    DROP CONSTRAINT Report_SubscriberPremium;

ALTER TABLE Report
    DROP CONSTRAINT Report_TechSupport;

ALTER TABLE ServerAdministrator
    DROP CONSTRAINT ServerAdministrator_Employee;

ALTER TABLE ServerFtp
    DROP CONSTRAINT ServerFtp_Service;

ALTER TABLE Server
    DROP CONSTRAINT Server_ServerAdministrator;

ALTER TABLE Service
    DROP CONSTRAINT Service_Server;

ALTER TABLE Service
    DROP CONSTRAINT Service_Subscriber;

ALTER TABLE SubscriberPremium
    DROP CONSTRAINT SubscriberPremium_Subscriber;

ALTER TABLE SubscriberStandard
    DROP CONSTRAINT SubscriberStandard_Subscriber;

ALTER TABLE Task
    DROP CONSTRAINT Task_Employee;

ALTER TABLE TechSupport
    DROP CONSTRAINT TechSupport_Employee;

ALTER TABLE Website
    DROP CONSTRAINT Website_Service;

-- tables
DROP TABLE Employee;

DROP TABLE Payment;

DROP TABLE Report;

DROP TABLE Server;

DROP TABLE ServerAdministrator;

DROP TABLE ServerFtp;

DROP TABLE Service;

DROP TABLE Subscriber;

DROP TABLE SubscriberPremium;

DROP TABLE SubscriberStandard;

DROP TABLE Task;

DROP TABLE Team;

DROP TABLE TechSupport;

DROP TABLE Website;

-- End of file.


-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-06-19 11:17:30.978

-- tables
-- Table: Employee
CREATE TABLE Employee (
    id int  NOT NULL,
    firstName varchar(255)  NOT NULL,
    lastName varchar(255)  NOT NULL,
    pesel char(11)  NOT NULL,
    salary decimal(10,2)  NOT NULL,
    Team_member_id int  NULL,
    Team_owner_id int  NULL,
    CONSTRAINT Employee_pk PRIMARY KEY (id)
);

-- Table: Payment
CREATE TABLE Payment (
    Service_id int  NOT NULL,
    Subscriber_id int  NOT NULL,
    dateAndTime timestamp  NOT NULL,
    currentPayment decimal(10,2)  NOT NULL,
    CONSTRAINT Payment_pk PRIMARY KEY (Service_id,Subscriber_id)
);

-- Table: Report
CREATE TABLE Report (
    SubscriberPremium_id int  NOT NULL,
    TechSupport_id int  NOT NULL,
    dateAndTimeOfRepeipt timestamp  NOT NULL,
    dateAndTimeOfEnd timestamp  NULL,
    lifespan bigint  NULL,
    content varchar(2048)  NOT NULL,
    status varchar(15)  NOT NULL,
    CONSTRAINT Report_pk PRIMARY KEY (SubscriberPremium_id,TechSupport_id,dateAndTimeOfRepeipt)
);

-- Table: Server
CREATE TABLE Server (
    id int  NOT NULL,
    name varchar(255)  NOT NULL,
    location varchar(255)  NOT NULL,
    serverMaxRamSize int  NOT NULL,
    serverMaxHardDriveSize decimal(10,2)  NOT NULL,
    ServerAdministrator_id int  NOT NULL,
    CONSTRAINT Server_pk PRIMARY KEY (id)
);

-- Table: ServerAdministrator
CREATE TABLE ServerAdministrator (
    Employee_id int  NOT NULL,
    CONSTRAINT ServerAdministrator_pk PRIMARY KEY (Employee_id)
);

-- Table: ServerFtp
CREATE TABLE ServerFtp (
    Service_id int  NOT NULL,
    price decimal(10,2)  NOT NULL,
    maxHardDriveSize decimal(10,2)  NOT NULL,
    CONSTRAINT ServerFtp_pk PRIMARY KEY (Service_id)
);

-- Table: Service
CREATE TABLE Service (
    id int  NOT NULL,
    ipAddress varchar(16)  NOT NULL,
    isPaid bool  NOT NULL,
    Subscriber_id int  NOT NULL,
    Server_id int  NOT NULL,
    CONSTRAINT Service_pk PRIMARY KEY (id)
);

-- Table: Subscriber
CREATE TABLE Subscriber (
    id int  NOT NULL,
    username varchar(255)  NOT NULL,
    funds decimal(10,2)  NOT NULL,
    CONSTRAINT Subscriber_pk PRIMARY KEY (id)
);

-- Table: SubscriberPremium
CREATE TABLE SubscriberPremium (
    Subscriber_id int  NOT NULL,
    CONSTRAINT SubscriberPremium_pk PRIMARY KEY (Subscriber_id)
);

-- Table: SubscriberStandard
CREATE TABLE SubscriberStandard (
    Subscriber_id int  NOT NULL,
    CONSTRAINT SubscriberStandard_pk PRIMARY KEY (Subscriber_id)
);

-- Table: Task
CREATE TABLE Task (
    id int  NOT NULL,
    content varchar(255)  NOT NULL,
    creationDateAndTime timestamp  NOT NULL,
    deadline timestamp  NOT NULL,
    Employee_id int  NOT NULL,
    CONSTRAINT Task_pk PRIMARY KEY (id)
);

-- Table: Team
CREATE TABLE Team (
    id int  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT Team_pk PRIMARY KEY (id)
);

-- Table: TechSupport
CREATE TABLE TechSupport (
    Employee_id int  NOT NULL,
    CONSTRAINT TechSupport_pk PRIMARY KEY (Employee_id)
);

-- Table: Website
CREATE TABLE Website (
    Service_id int  NOT NULL,
    domainName varchar(63)  NOT NULL,
    price decimal(10,2)  NOT NULL,
    maxHardDriveSize decimal(10,2)  NOT NULL,
    CONSTRAINT Website_pk PRIMARY KEY (Service_id)
);

-- foreign keys
-- Reference: Employee_Team_Member (table: Employee)
ALTER TABLE Employee ADD CONSTRAINT Employee_Team_Member
    FOREIGN KEY (Team_member_id)
    REFERENCES Team (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Employee_Team_Supervisor (table: Employee)
ALTER TABLE Employee ADD CONSTRAINT Employee_Team_Supervisor
    FOREIGN KEY (Team_owner_id)
    REFERENCES Team (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Payment_Service (table: Payment)
ALTER TABLE Payment ADD CONSTRAINT Payment_Service
    FOREIGN KEY (Service_id)
    REFERENCES Service (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Payment_Subscriber (table: Payment)
ALTER TABLE Payment ADD CONSTRAINT Payment_Subscriber
    FOREIGN KEY (Subscriber_id)
    REFERENCES Subscriber (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Report_SubscriberPremium (table: Report)
ALTER TABLE Report ADD CONSTRAINT Report_SubscriberPremium
    FOREIGN KEY (SubscriberPremium_id)
    REFERENCES SubscriberPremium (Subscriber_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Report_TechSupport (table: Report)
ALTER TABLE Report ADD CONSTRAINT Report_TechSupport
    FOREIGN KEY (TechSupport_id)
    REFERENCES TechSupport (Employee_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: ServerAdministrator_Employee (table: ServerAdministrator)
ALTER TABLE ServerAdministrator ADD CONSTRAINT ServerAdministrator_Employee
    FOREIGN KEY (Employee_id)
    REFERENCES Employee (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: ServerFtp_Service (table: ServerFtp)
ALTER TABLE ServerFtp ADD CONSTRAINT ServerFtp_Service
    FOREIGN KEY (Service_id)
    REFERENCES Service (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Server_ServerAdministrator (table: Server)
ALTER TABLE Server ADD CONSTRAINT Server_ServerAdministrator
    FOREIGN KEY (ServerAdministrator_id)
    REFERENCES ServerAdministrator (Employee_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Service_Server (table: Service)
ALTER TABLE Service ADD CONSTRAINT Service_Server
    FOREIGN KEY (Server_id)
    REFERENCES Server (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Service_Subscriber (table: Service)
ALTER TABLE Service ADD CONSTRAINT Service_Subscriber
    FOREIGN KEY (Subscriber_id)
    REFERENCES Subscriber (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: SubscriberPremium_Subscriber (table: SubscriberPremium)
ALTER TABLE SubscriberPremium ADD CONSTRAINT SubscriberPremium_Subscriber
    FOREIGN KEY (Subscriber_id)
    REFERENCES Subscriber (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: SubscriberStandard_Subscriber (table: SubscriberStandard)
ALTER TABLE SubscriberStandard ADD CONSTRAINT SubscriberStandard_Subscriber
    FOREIGN KEY (Subscriber_id)
    REFERENCES Subscriber (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Task_Employee (table: Task)
ALTER TABLE Task ADD CONSTRAINT Task_Employee
    FOREIGN KEY (Employee_id)
    REFERENCES Employee (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: TechSupport_Employee (table: TechSupport)
ALTER TABLE TechSupport ADD CONSTRAINT TechSupport_Employee
    FOREIGN KEY (Employee_id)
    REFERENCES Employee (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Website_Service (table: Website)
ALTER TABLE Website ADD CONSTRAINT Website_Service
    FOREIGN KEY (Service_id)
    REFERENCES Service (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.







INSERT INTO employee (id, firstname, lastname, pesel, salary, team_member_id, team_owner_id) VALUES (1, 'Kamil', 'Melasa', '98958675847', 3000, null, null);

INSERT INTO serveradministrator (employee_id) VALUES (1);

INSERT INTO server (id, name, location, servermaxramsize, servermaxharddrivesize, serveradministrator_id) VALUES (1, 'JEDEN', 'Warszawa', 128, 4048, 1);


INSERT INTO subscriber (id, username, funds) VALUES (1, 's20656', 55);
INSERT INTO subscriber (id, username, funds) VALUES (2, 't0m@sz', 310);
INSERT INTO subscriber (id, username, funds) VALUES (3, 'tester', 0);

INSERT INTO subscriberstandard (subscriber_id) SELECT id FROM subscriber WHERE username='s20656';
INSERT INTO subscriberstandard (subscriber_id) SELECT id FROM subscriber WHERE username='t0m@sz';
INSERT INTO subscriberpremium (subscriber_id) SELECT id FROM subscriber WHERE username='tester';


INSERT INTO service (id, ipaddress, ispaid, subscriber_id, server_id) VALUES (1, '135.118.167.164', false, 2, 1);
INSERT INTO service (id, ipaddress, ispaid, subscriber_id, server_id) VALUES (2, '43.44.56.98', false, 2, 1);
INSERT INTO service (id, ipaddress, ispaid, subscriber_id, server_id) VALUES (3, '116.128.93.219', false, 3, 1);
INSERT INTO service (id, ipaddress, ispaid, subscriber_id, server_id) VALUES (4, '23.203.208.10', false, 3, 1);
INSERT INTO service (id, ipaddress, ispaid, subscriber_id, server_id) VALUES (5, '103.139.153.83', false, 3, 1);


INSERT INTO website (service_id, domainname, price, maxharddrivesize) VALUES (1, 'kochamjave', 99, 32);
INSERT INTO website (service_id, domainname, price, maxharddrivesize) VALUES (3, 'testowa', 99, 32);
INSERT INTO website (service_id, domainname, price, maxharddrivesize) VALUES (5, 'mojawlasnastrona', 99, 32);

INSERT INTO serverftp (service_id, price, maxharddrivesize) VALUES (2, 119, 512);
INSERT INTO serverftp (service_id, price, maxharddrivesize) VALUES (4, 119, 512);






