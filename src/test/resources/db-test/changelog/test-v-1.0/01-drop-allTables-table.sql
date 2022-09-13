
    alter table pet_message 
       drop 
       foreign key FKettrg217y0w73l13ek20ekjdm
GO
    
    alter table pet_notes 
       drop 
       foreign key FK6bgt22xokk10h3t6btnf109ey
GO
    
    alter table pet_users_role 
       drop 
       foreign key FKgcfa7899kjug2vx2727tfd4o7
GO
    
    alter table users_wallet 
       drop 
       foreign key FKgahc2l7skx7hm9kmnr10elf2a
GO
    
    alter table wallet_finance 
       drop 
       foreign key FKpy685yny0w86063dxh06au4xi
GO
    
    drop table if exists hibernate_sequence
GO
    
    drop table if exists pet_message
GO
    
    drop table if exists pet_notes
GO
    
    drop table if exists pet_user
GO
    
    drop table if exists pet_users_role
GO
    
    drop table if exists users_wallet
GO
    
    drop table if exists wallet_finance
GO