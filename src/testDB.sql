# 创建househz数据库
create database househz;

# 切换到househz数据库
use househz;

# 创建表test2househz
create table test2househz(
HouseName varchar(20),
Address varchar(60),
price varchar(6)
)

# 获取所有的房产信息
select * from test2househz;

# 获取地址不为空的房产
select * from test2househz
where Address != "";

# 获取下沙的房价
select * from test2househz
where Address like "%下沙%";

# 获取下沙的平均房价
select avg(price)
from test2househz
where Address like "%下沙%";


