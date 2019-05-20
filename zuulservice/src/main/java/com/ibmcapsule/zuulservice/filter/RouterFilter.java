package com.ibmcapsule.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class RouterFilter extends ZuulFilter {
  @Override
  public String filterType() {
    return "router";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    System.out.println("Inside Router Filter");
    return null;
  }
}
