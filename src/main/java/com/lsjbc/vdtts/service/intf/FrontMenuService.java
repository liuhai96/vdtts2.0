package com.lsjbc.vdtts.service.intf;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FrontMenuService {
    public List selectMenuList(HttpServletRequest request);
}
