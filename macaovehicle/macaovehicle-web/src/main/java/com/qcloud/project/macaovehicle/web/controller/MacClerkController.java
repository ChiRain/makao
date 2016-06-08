package com.qcloud.project.macaovehicle.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.common.ClerkConstant;
import com.qcloud.component.organization.model.Clerk;
import com.qcloud.component.organization.web.helper.ClerkHelper;
import com.qcloud.component.permission.AccountClient;
import com.qcloud.component.permission.PermissionClient;
import com.qcloud.component.permission.QPermission;
import com.qcloud.component.permission.QRole;
import com.qcloud.component.permission.model.Account;
import com.qcloud.component.permission.model.RolePermission;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = MacClerkController.DIR)
public class MacClerkController {

    public static final String DIR = "/macClerk";

    @Autowired
    public OrganizationClient  organizationClient;

    @Autowired
    public PermissionClient    permissionClient;

    @Autowired
    private AccountClient      accountClient;

    @Autowired
    private ClerkHelper        clerkHelper;

    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private FileSDKClient      fileSDKClient;

    /**
     * 新增角色信息.
     * @param request
     * @param form
     * @return
     */
    @RequestMapping
    public FrontAjaxView addClerk(HttpServletRequest request, Clerk clerk, Long departmentId, Long roleId, String pwd1, String pwd2) {

        AssertUtil.assertNotNull(clerk.getMobile(), "电话不能为空.");
        AssertUtil.greatZero(departmentId, "部门不能为空.");
        AssertUtil.greatZero(roleId, "角色不能为空.");
        AssertUtil.assertTrue(pwd1.equals(pwd2), "密码不一致, 请重新输入.");
        // 新增用户
        organizationClient.registClerk(clerk, departmentId, pwd1);
        // 新增用户角色授权
        String accountCode = ClerkConstant.CLERKPREFIXCODE + clerk.getMobile();
        Account account = accountClient.getAccount(accountCode);
        permissionClient.grant(account.getId(), roleId);
        FrontAjaxView view = new FrontAjaxView();
        return view;
    }

    /**
     * 用户权限菜单
     * @param request
     * @return
     */
    @RequestMapping
    public FrontAjaxView menuList(HttpServletRequest request) {

        Clerk clerk = clerkHelper.getClerk(request);
        String accountCode = ClerkConstant.CLERKPREFIXCODE + clerk.getMobile();
        // 角色
        List<QRole> qRoles = permissionClient.listRoleByAccount(accountCode);
        // 分类资源
        List<Classify> classifys = new ArrayList<Classify>();
        if (qRoles.size() > 0) {
            List<RolePermission> rolePermissions = qRoles.get(0).getRolePermissions();
            // 权限permissionId
            for (RolePermission rolePermission : rolePermissions) {
                long permissionId = rolePermission.getPermissionId();
                QPermission qPermission = permissionClient.getPermission(permissionId);
                Classify classify = publicdataClient.getClassify(qPermission.getTargetId());
                classifys.add(classify);
            }
        }
        List<QClassify> classifyList = publicdataClient.listClassifyForTree(classifys);
        for (QClassify qClassify : classifyList) {
            fillFileServerUrlBeforeImage(qClassify);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("classifyList", classifyList);
        view.setMessage("获取分类成功");
        return view;
    }

    private void fillFileServerUrlBeforeImage(QClassify qClassify) {

        if (StringUtils.isNotEmpty(qClassify.getImage())) {
            qClassify.setImage(fileSDKClient.getFileServerUrl() + qClassify.getImage());
        } else {
            qClassify.setImage("");
        }
        List<QClassify> children = qClassify.getChildrenList();
        for (QClassify c : children) {
            fillFileServerUrlBeforeImage(c);
        }
    }
}
