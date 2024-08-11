\c transaction_db
insert into master.card_type values (1,'Rewards', 10000, 50000, now(), now());
insert into master.card_type values (2,'Signature', 20000, 80000, now(), now());
insert into master.card_type values (3,'Travel', 50000, 200000, now(), now());

insert into master.cards values ('1111222233334444', 1, 200000, 200000,null,null,true,NOW(), now());
insert into master.cards values ('1111222255554444', 1, 200000, 200000,null,null,true,NOW(), now());
insert into master.cards values ('1111662333664444', 1, 200000, 200000,null,null,true,NOW(), now());
insert into master.cards values ('1111223563334444', 1, 200000, 200000,null,null,true,NOW(), now());
insert into master.cards values ('1111662335534444', 2, 500000, 500000,null,null,true,NOW(), now());
insert into master.cards values ('1111882333399944', 2, 500000, 500000,null,null,true,NOW(), now());
insert into master.cards values ('1111277733334444', 2, 500000, 500000,null,null,true,NOW(), now());
insert into master.cards values ('1111211133334444', 2, 500000, 500000,null,null,true,NOW(), now());
insert into master.cards values ('1111999233334444', 3, 2000000, 2000000,null,null,true,NOW(), now());
insert into master.cards values ('1111200055544444', 3, 2000000, 2000000,null,null,true,NOW(), now());
insert into master.cards values ('1111222234565444', 3, 2000000, 2000000,null,null,true,NOW(), now());
insert into master.cards values ('1111567876564444', 3, 2000000, 2000000,null,null,true,NOW(), now());
insert into master.cards values ('1111234556734454', 3, 2000000, 2000000,null,null,true,NOW(), now());

insert into merchant values(1, 'Test1', 'Test', true, now(), now());
insert into merchant values(2, 'Test2', 'Test', true, now(), now());
insert into merchant values(3, 'Test3', 'Test', true, now(), now());
insert into merchant values(4, 'Test4', 'Test', true, now(), now());
insert into merchant values(5, 'Test5', 'Test', true, now(), now());
insert into merchant values(6, 'Test6', 'Test', true, now(), now());
insert into merchant values(7, 'Test7', 'Test', true, now(), now());
insert into merchant values(8, 'Test8', 'Test', true, now(), now());
insert into merchant values(9, 'Test9', 'Test', true, now(), now());
insert into merchant values(10, 'Test10', 'Test', true, now(), now());
insert into merchant values(11, 'Test11', 'Test', true, now(), now());
insert into merchant values(12, 'Test12', 'Test', true, now(), now());
insert into merchant values(13, 'Test13', 'Test', true, now(), now());

