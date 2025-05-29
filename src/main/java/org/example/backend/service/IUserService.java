package org.example.backend.service;

import org.example.backend.dto.LoginReq;
import org.example.backend.dto.RegisterReq;
import org.example.backend.exception.ServiceException;

public interface IUserService {
    Integer register(RegisterReq req) throws ServiceException;
    String login(LoginReq req) throws ServiceException;
}
