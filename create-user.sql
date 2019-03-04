CREATE USER 'springadmin'@'localhost' IDENTIFIED BY 'springadmin';

GRANT ALL PRIVILEGES ON * . * TO 'springadmin'@'localhost';

# Starting with MySQL 8.0.4, the MySQL team changed the 
# default authentication plugin for MySQL server 
# from mysql_native_password to caching_sha2_password.

ALTER USER 'springadmin'@'localhost' IDENTIFIED WITH mysql_native_password BY 'springadmin';