drop table if exists member CASCADE;

CREATE TABLE `member` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) ,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`)
)
COLLATE='utf8_general_ci'
;