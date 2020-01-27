package com.ning.service.impl;

import com.ning.mapper.UsersMapper;
import com.ning.pojo.Users;
import com.ning.service.UsersService;

public class UsersServiceImpl implements UsersService {
    private UsersMapper usersMapper;

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public Users login(Users users) {
        return usersMapper.selByUsersPwd(users);
    }
}
