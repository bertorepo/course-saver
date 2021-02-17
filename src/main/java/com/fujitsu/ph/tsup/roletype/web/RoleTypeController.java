/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.web;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
//1.0.2   | 2021/02/17 | WS) c.sinda           | Updated
//==================================================================================================

/**
 * <pre>
 * This is the implementation of Role Type Controller.
 * </pre>
 * 
 * @author rl.naval
 * @version 0.01
 *
 */
@Controller
@RequestMapping("/roletype")
public class RoleTypeController {

    @Autowired
    RoleTypeService roleTypeService;

    /**
     * Load the Role Type on the screen
     * 
     * @param model Model
     * @return View
     */
    @GetMapping("/load")
    public String manageRoleType(Model model) {
        Set<RoleType> roletype = roleTypeService.loadAllRoleType();
        List<RoleType> roletypeList = roletype.stream().collect(Collectors.toList());

        model.addAttribute("roletypeList", roletypeList);

        return "roletype-management/roleTypeView";
    }

    /**
     * Method for getting role type id to delete
     * 
     * @param id Role Type id
     * @param form RoleType Form
     * @param bindingResult Binding Result
     * @param model Model
     * @return View
     */
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

    /**
     * Method for deleting role with the given id
     * 
     * @param id role id
     * @param redirectAttributes RedirectAttributes
     * @param model Model
     * @return View
     */
    @PostMapping("/{roleId}/delete")
    public String submitDeleteRoleTypeform(@PathVariable("roleId") Long id,
            RedirectAttributes redirectAttributes, Model model) {

        // Call deleteRoleTypeById() method
        roleTypeService.deleteRoleTypeById(id);
        redirectAttributes.addFlashAttribute("deleteSuccessMessage",
                "You have successfully deleted this role type");

        return "redirect:/roletype/load#successModal";
    }

    /**
     * Method for searching role type
     * 
     * @param searchRoleName Role name
     * @param model Model
     * @return View
     */
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

    /**
     * Create the role type. Method = GET
     * 
     * @param model Model
     * @return roleTypeForm and view
     */
    @GetMapping("/create")
    public String showCreateRoleTypeForm(Model model) {
        model.addAttribute("create");
        return "roletype-management/roleTypeCreate";
    }

    /**
     * Create the role type. Method = POST
     * 
     * @param form RoleTypeForm
     * @param bindingResult BindingResult
     * @param model Model
     * @return RoleTypeForm and view
     */
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

    /**
     * Update the role type. Method = GET
     * 
     * @param id roleId
     * @param form RoleTypeForm
     * @param model Model
     * @return RoleTypeForm and view
     */
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

    /**
     * Update the role type. Method = POST
     * 
     * @param id roleId
     * @param form RoleTypeForm
     * @param model Model
     * @return RoleTypeForm and view
     */
    @PostMapping("/update/{roleId}")
    public String submitUpdateRoleTypeForm(@PathVariable("roleId") Long id, RoleTypeForm form, Model model) {

        Set<RoleType> roleSize = roleTypeService.findIfRoleNameExists(form.getRolename(), id);

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(form.getRolename());
        boolean invalidRoleName = m.find();

        if (!(roleSize == null)) {
            model.addAttribute("roleNameError", "The role name already exists");

            if (form.getRoledesc().length() > 120) {
                model.addAttribute("roleDescError",
                        "Role Description is too long, please shorten the role description");

            } else if (form.getRoledesc() == "") {
                model.addAttribute("roleDescError", "Please enter a Role Description");

            }
            form.setId(id);
            model.addAttribute("updateRoleTypeForm", form);

            return "roletype-management/roleTypeUpdate";
        } else if (roleSize == null) {
            if (form.getRolename() == "" || form.getRolename().length() > 40 || invalidRoleName
                    || form.getRoledesc() == "" || form.getRoledesc().length() > 120) {
                if (form.getRolename().length() > 40) {
                    model.addAttribute("roleNameError",
                            "Role Name is too long, please shorten the role name");

                } else if (invalidRoleName) {

                    model.addAttribute("roleNameError", "Invalid Role Name");

                } else if (form.getRolename() == "") {

                    model.addAttribute("roleNameError", "Please enter a Role Name");

                }

                if (form.getRoledesc().length() > 120) {
                    model.addAttribute("roleDescError",
                            "Role Description is too long, please shorten the role description");

                } else if (form.getRoledesc() == "") {
                    model.addAttribute("roleDescError", "Please enter a Role Description");

                }

                form.setId(id);
                model.addAttribute("updateRoleTypeForm", form);

                return "roletype-management/roleTypeUpdate";
            } else {

                RoleType updatedRoleType = new RoleType.Builder(form.getRolename(), form.getRoledesc())
                        .build();
                roleTypeService.updateRoleType(id, updatedRoleType);
            }

        }
        return "redirect:/roletype/load";
    }

}