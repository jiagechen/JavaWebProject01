package cn.njust.label.demo.service;

import cn.njust.label.demo.dto.UmsAdminDto;
import cn.njust.label.model.UmsAdmin;

import java.util.List;

/**
 * DemoService接口
 */
public interface DemoService {
    List<UmsAdmin> listAllAdmin();

    int createAdmin(UmsAdminDto umsAdminDto);

    int updateAdmin(Long id, UmsAdminDto umsAdminDto);

    int deleteAdmin(Long id);

    List<UmsAdmin> listAdmin(int pageNum, int pageSize);

    UmsAdmin getAdmin(Long id);
}
