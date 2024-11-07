# MariaDB
FROM mariadb:latest

# 환경 변수 설정
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=purepoint
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root

# 컨테이너의 3306 포트 노출
EXPOSE 3306

# MariaDB 시작
CMD ["mysqld"]