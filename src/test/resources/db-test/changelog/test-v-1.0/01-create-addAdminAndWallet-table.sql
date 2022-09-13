insert into pet_user (id, username, name, surname, password, active)
  values (1, 'admin','eron','ryabets', '$2a$12$n4WhU3P6kieGQWCHlFZmweAfsMu2qTwqpW2EvwHcQKOwygM9fI.xy', true)

GO
insert into pet_users_role (user_id, roles)
  values (1, 'USER'), (1, "ADMIN")

GO
insert
    into
        users_wallet
        (balance, currency, type, wallet_name, wallet_id, id)
    values
        (1200, 'USD', 'DEBIT', 'MyDebitWallet', 2, 1)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( 10, 1210, '2022-01-01 10:54:05.653000', 'INCOME', '2022-01-01 10:54:05',2, 3)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( 20, 1230, '2022-02-01 10:54:05.653000', 'INCOME', '2022-02-01 10:54:05',2, 4)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( 35, 1245, '2022-03-01 10:54:05.653000', 'INCOME', '2022-03-01 10:54:05',2, 5)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( -20, 1225, '2022-04-01 10:54:05.653000', 'INCOME', '2022-04-01 10:54:05',2, 6)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( -10, 1215, '2022-05-01 10:54:05.653000', 'INCOME', '2022-05-01 10:54:05',2, 7)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( 200, 1410, '2022-06-01 10:54:05.653000', 'INCOME', '2022-06-01 10:54:05',2, 8)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( -140, 1270, '2022-07-01 10:54:05.653000', 'INCOME', '2022-07-01 10:54:05',2, 9)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( 50, 1320, '2022-08-01 10:54:05.653000', 'INCOME', '2022-08-01 10:54:05',2, 10)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( 80, 1400, '2022-09-01 10:54:05.653000', 'INCOME', '2022-09-01 10:54:05',2, 11)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( -125, 1275, '2022-10-01 10:54:05.653000', 'INCOME', '2022-10-01 10:54:05',2, 12)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( 60, 1335, '2022-11-01 10:54:05.653000', 'INCOME', '2022-11-01 10:54:05',2, 13)
GO
insert
    into
        wallet_finance
        (amount, balance, date, operation, string_date, wallet_id, id)
    values
        ( 100, 1435, '2022-12-01 10:54:05.653000', 'INCOME', '2022-12-01 10:54:05',2, 14)
GO
update
        users_wallet
    set
        balance=1435
    where
        wallet_id=2

GO
    insert
    into
        pet_message
        (user_id, date, filename, string_date, tag, text, id)
    values
        (1, '2022-12-01 10:54:05.653000', NULL , '2022-12-01 10:54:05', 'car', 'nice car 1', 15)

GO
    insert
    into
        pet_message
        (user_id, date, filename, string_date, tag, text, id)
    values
        (1, '2022-12-01 10:54:05.653000', NULL , '2022-12-01 10:54:05', 'car', 'nice car 2', 16)

GO
    insert
    into
        pet_message
        (user_id, date, filename, string_date, tag, text, id)
    values
        (1, '2022-12-01 10:54:05.653000', NULL , '2022-12-01 10:54:05', 'cat', 'nice cat 1', 17)

GO
    insert
    into
        pet_message
        (user_id, date, filename, string_date, tag, text, id)
    values
        (1, '2022-12-01 10:54:05.653000', NULL , '2022-12-01 10:54:05', 'cat', 'nice cat 2', 18)

GO
    insert
    into
        pet_message
        (user_id, date, filename, string_date, tag, text, id)
    values
        (1, '2022-12-01 10:54:05.653000', NULL , '2022-12-01 10:54:05', 'cat', 'nice cat 3', 19)

GO
    insert
    into
        pet_message
        (user_id, date, filename, string_date, tag, text, id)
    values
        (1, '2022-12-01 10:54:05.653000', NULL , '2022-12-01 10:54:05', 'cat', 'nice cat 4', 20)
