\c transaction_db;

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

insert into master.merchant values(1, 'Test1', 'Test', true, now(), now());
insert into master.merchant values(2, 'Test2', 'Test', true, now(), now());
insert into master.merchant values(3, 'Test3', 'Test', true, now(), now());
insert into master.merchant values(4, 'Test4', 'Test', true, now(), now());
insert into master.merchant values(5, 'Test5', 'Test', true, now(), now());
insert into master.merchant values(6, 'Test6', 'Test', true, now(), now());
insert into master.merchant values(7, 'Test7', 'Test', true, now(), now());
insert into master.merchant values(8, 'Test8', 'Test', true, now(), now());
insert into master.merchant values(9, 'Test9', 'Test', true, now(), now());
insert into master.merchant values(10, 'Test10', 'Test', true, now(), now());
insert into master.merchant values(11, 'Test11', 'Test', true, now(), now());
insert into master.merchant values(12, 'Test12', 'Test', true, now(), now());
insert into master.merchant values(13, 'Test13', 'Test', true, now(), now());


--insert into cards values (select array_to_string(array(select chr((48+round(random()*9))::integer) from generate_series(1,16)),''), 1, select  floor(random()*100000), 200000, ,,true,NOW(), now());

--do $$
--begin
--	for r in 1..100 loop
--		declare _cardNum char := select array_to_string(,array(select chr((48+round(random()*9))::integer) from generate_series(1,16)),'');
--		insert into cards values (_cardNum, 1, select  floor(random()*100000), 0, ,,true,NOW(), now());
--	end loop
--	for r in 1..100 loop
--		insert into cards values (select array_to_string(array(select chr((48+round(random()*9))::integer) from generate_series(1,16)),''), 2, select  floor(random()*1000000), 0, ,,true,NOW(), now());
--	end loop
--	for r in 1..100 loop
--		insert into cards values (select array_to_string(array(select chr((48+round(random()*9))::integer) from generate_series(1,16)),''), 3, select  floor(random()*1000000), 0, ,,true,NOW(), now());
--	end loop
--end $$;
