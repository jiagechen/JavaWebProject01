package cn.njust.label.demo.service.impl;

import cn.njust.label.demo.service.DemoService;
import cn.njust.label.mapper.UmsAdminMapper;
import cn.njust.label.model.UmsAdmin;
import cn.njust.label.model.UmsAdminExample;
import com.github.pagehelper.PageHelper;
import cn.njust.label.demo.dto.UmsAdminDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DemoService实现类
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private UmsAdminMapper adminMapper;

    @Override
    public List<UmsAdmin> listAllAdmin() {
        return adminMapper.selectByExample(new UmsAdminExample());
    }

    @Override
    public int createAdmin(UmsAdminDto umsAdminDto) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminDto, umsAdmin);
        return adminMapper.insertSelective(umsAdmin);
    }

    @Override
    public int updateAdmin(Long id, UmsAdminDto umsAdminDto) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminDto, umsAdmin);
        umsAdmin.setId(id);
        return adminMapper.updateByPrimaryKeySelective(umsAdmin);
    }

    @Override
    public int deleteAdmin(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> listAdmin(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectByExample(new UmsAdminExample());
    }

    @Override
    public UmsAdmin getAdmin(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }
}
