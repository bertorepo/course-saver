/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.web;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.roletype.domain.RoleType;
import com.fujitsu.ph.tsup.roletype.model.RoleTypeForm;
import com.fujitsu.ph.tsup.roletype.service.RoleTypeService;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Role Type Management
//Class Name   : RoleTypeController.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//1.0.0   | 2021/02/05 | WS) rl.naval          | Initial Version
//1.0.1   | 2021/02/15 | WS) rl.naval          | Updated
//==================================================================================================

@Controller
@RequestMapping("/roletype")
public class RoleTypeController {

    @Autowired
    RoleTypeService roleTypeService;

    @GetMapping("/load")
    public String manageRoleType(Model model) {
        Set<RoleType> roletype = roleTypeService.loadAllRoleType();
        List<RoleType> roletypeList = roletype.stream().collect(Collectors.toList());

        model.addAttribute("roletypeList", roletypeList);

        return "roletype-management/roleTypeView";
    }

    @GetMapping("/{roleId}/delete")
    public String showDeleteRoleTypeForm(@RequestParam(value = "roleIdInput") Long id, RoleTypeForm form,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/roletype/load?roleId=" + id + "#confirmModal";
        }

        // Set Value for RoleType Object
        RoleType role = roleTypeService.findRoleById(id);

        // Set Value for RoleTypeForm
        form.setId(role.getId());
        form.setRolename(role.getRolename());
        form.setRoledesc(role.getRoledesc());

        model.addAttribute("deleteRoleTypeForm", form);

        return "redirect:/roletype/load?roleId=" + id + "#confirmModal";
    }

    @PostMapping("/{roleId}/delete")
    public String submitDeleteRoleTypeform(@PathVariable("roleId") Long id,
            RedirectAttributes redirectAttributes, Model model) {

        // Call deleteRoleTypeById() method
        roleTypeService.deleteRoleTypeById(id);
        redirectAttributes.addFlashAttribute("deleteSuccessMessage",
                "You have successfully deleted this role type");

        return "redirect:/roletype/load#successModal";
    }

    @PostMapping("/search")
    public String submitSearchRoleTypeForm(@RequestParam(name = "searchRole") String searchRole,
            Model model) {

        if (searchRole.isEmpty()) {
            return "redirect:/roletype/load";
        }

        Set<RoleType> role = roleTypeService.findRoleTypeByKeyword(searchRole);
        List<RoleType> listOfRole = role.stream().collect(Collectors.toList());

        model.addAttribute("roletypeList", listOfRole);

        return "roletype-management/roleTypeView";
    }

    @GetMapping("/create")
    public String showCreateRoleTypeForm(Model model) {
        model.addAttribute("create");
        return "roletype-management/roleTypeCreate";
    }

    @PostMapping("/create")
    public String submitCreateRoleTypeForm(RoleTypeForm form, BindingResult bindingResult, Model model) {

        Set<RoleType> roleSize = roleTypeService.findRoleTypeByName(form.getRolename());
        if (roleSize == null) {
            RoleType roleDetails = new RoleType.Builder(form.getRolename(), form.getRoledesc()).build();
            roleTypeService.createRoleType(roleDetails);
        } else {
            model.addAttribute("successMessage", "The Role Type is already existing");
        }

        return "roletype-management/roleTypeCreate";
    }

    @GetMapping("/update/{roleId}")
    public String showUpdateRoleTypeForm(@PathVariable("roleId") Long id, RoleTypeForm form, Model model) {

        // Set Value for RoleType Object
        RoleType role = roleTypeService.findRoleById(id);

        // Set Value for RoleTypeForm
        form.setId(role.getId());
        form.setRolename(role.getRolename());
        form.setRoledesc(role.getRoledesc());

        model.addAttribute("updateRoleTypeForm", form);

        return "roletype-management/roleTypeUpdate";
    }

    @PostMapping("/update/{roleId}")
    public String submitUpdateRoleTypeForm(@PathVariable("roleId") Long id, RoleTypeForm form,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            form.setId(id);
            return "roletype-management/roleTypeUpdate";
        }

        Set<RoleType> roletypeSize = roleTypeService.findRoleTypeByName(form.getRolename());

        if (roletypeSize == null) {

            RoleType roletypeDetails = new RoleType.Builder(form.getRolename(), form.getRoledesc()).build();
            roleTypeService.updateRoleType(roletypeDetails);

        } else {
            model.addAttribute("successMessage", "The course is already existing.");
        }

        return "roletype/load";

    }

}
