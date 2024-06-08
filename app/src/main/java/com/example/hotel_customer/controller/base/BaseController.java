package com.example.hotel_customer.controller.base;

import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.view.base.View;

public class BaseController<V extends View,R extends Repository> implements Controller {
    protected V view;
    protected R repository;
}
