CREATE TABLE PUBLIC.DOSSIER_BATCH_JOB
(ID IDENTITY,
UUID VARCHAR(32) NOT NULL UNIQUE,
JOB_TYPE VARCHAR(50) NOT NULL,
PRIORITY SMALLINT NOT NULL DEFAULT 0,  
CREATE_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
DESCRIPTION VARCHAR(1000),
STATUS CHAR(3) NOT NULL DEFAULT 'NEW',
RESULT VARCHAR(2000));

ALTER TABLE PUBLIC.DOSSIER_BATCH_JOB
ADD CONSTRAINT chk_status CHECK (STATUS in ('NEW', 'PND','RUN','FLD', 'CPT'));

CREATE TABLE PUBLIC.DOSSIER_BATCH_PARAM
(ID IDENTITY,
JOB_ID BIGINT NOT NULL,
PARAM_KEY VARCHAR(30) NOT NULL,
PARAM_VAL VARCHAR(1000) NOT NULL
);

ALTER TABLE PUBLIC.DOSSIER_BATCH_PARAM
	ADD FOREIGN KEY (JOB_ID) 
	REFERENCES PUBLIC.DOSSIER_BATCH_JOB (ID)
	ON DELETE CASCADE 
	ON UPDATE NO ACTION; 


CREATE TABLE PUBLIC.DOSSIER_BATCH_LOG
(ID IDENTITY,
JOB_ID BIGINT NOT NULL,
LOG_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
LOG_USERID VARCHAR(25) NOT NULL DEFAULT CURRENT_USER(),
LOG_TYPE VARCHAR(50) NOT NULL,
LOG_DATA VARCHAR(5000) NOT NULL,
);

ALTER TABLE PUBLIC.DOSSIER_BATCH_LOG
	ADD FOREIGN KEY (JOB_ID)
	REFERENCES PUBLIC.DOSSIER_BATCH_JOB (ID)
	ON DELETE CASCADE
	ON UPDATE NO ACTION;


	