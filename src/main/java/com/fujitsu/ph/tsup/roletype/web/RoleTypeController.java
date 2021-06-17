/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.web;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
//0.01    | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02    | 2021/02/15 | WS) rl.naval          | Updated
//0.03    | 2021/02/17 | WS) c.sinda           | Updated
//0.04    | 2021/02/18 | WS) c.rondina         | Updated
//0.05    | 2021/02/21 | WS) j.sayaboc         | Updated
//0.06    | 2021/02/24 | WS) p.cui             | Updated
//0.07    | 2021/02/26 | WS) c.sinda           | Updated
//0.08    | 2021/03/11 | WS) p.cui             | Updated
//0.09    | 2021/03/11 | WS) j.sayaboc         | Updated
//0.10    | 2021/03/18 | WS) rl.naval          | Updated
//0.11	  | 2021/06/08 | WS) r.gaquit		   | Updated
//==================================================================================================
/**
 * <pre>
 * This is the implementation of Role Type Controller.
 * </pre>
 * 
 * @version 0.11
 * @author rl.naval
 * @author c.sinda
 * @author c.rondina
 * @author p.cui
 * @author j.sayaboc
 * @author r.gaquit
 *
 */
@Controller
@RequestMapping("/roletype")
public class RoleTypeController {
    @Autowired
    private RoleTypeService roleTypeService;

    /**
     * Load the Role Type on the screen
     * 
     * @param model Model
     * @return View
     */
    @GetMapping("/load/{page}")
    public String manageRoleType(@PathVariable("page") int page, Model model) {
        int pageSize = 6;
        int totalRoleTypes = 0;
        int totalPage = 0;

        Set<RoleType> allRoleTypes = roleTypeService.loadAllRoleType();
        totalRoleTypes = allRoleTypes.size();

        Set<RoleType> roletype = roleTypeService.loadAllRoleType(pageSize, page);
        List<RoleType> roletypeList = roletype.stream().collect(Collectors.toList());

        //Compute number of pages for pagination
        if ((totalRoleTypes % pageSize) != 0) {
            totalPage = (int) (totalRoleTypes / pageSize) + 1;
        } else {
            totalPage = (int) (totalRoleTypes / pageSize);
        }
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("roletypeList", roletypeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("localStorage", model.getAttribute("localStorage"));

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
            return "redirect:/roletype/load/1?roleId=" + id + "#confirmModal";
        }

        // Set Value for RoleType Object
        RoleType role = roleTypeService.findRoleById(id);

        // Set Value for RoleTypeForm
        form.setId(role.getId());
        form.setRolename(role.getRolename());
        form.setRoledesc(role.getRoledesc());
        model.addAttribute("deleteRoleTypeForm", form);
        return "redirect:/roletype/load/1?roleId=" + id + "#confirmModal";
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
        redirectAttributes.addFlashAttribute("message",
                "You have successfully deleted this role type");
        return "redirect:/roletype/load/1#successModal";
    }

    /**
     * Method for searching role type
     * 
     * @param page
     * @param searchRoleName Role name
     * @param model Model
     * @return View
     */
    @GetMapping("/search/{page}")
    public String submitSearchRoleTypeForm(@PathVariable("page") int page, @RequestParam(name = "searchRole") String searchRole,
            Model model) {
        int pageSize = 6;
        int searchtotalRoleTypes = 0;
        int searchtotalPage = 0;

        Set<RoleType> allRoleTypes = roleTypeService.findRoleTypeByKeyword(searchRole);
        Set<RoleType> role = roleTypeService.findRoleTypeByKeyword(searchRole,pageSize,page);
        List<RoleType> listOfRole = role.stream().collect(Collectors.toList());
        searchtotalRoleTypes = allRoleTypes.size();

        //Compute number of pages for pagination
        if ((searchtotalRoleTypes % pageSize) != 0) {
            searchtotalPage = (int) (searchtotalRoleTypes / pageSize) + 1;
        } else {
            searchtotalPage = (int) (searchtotalRoleTypes / pageSize);
        }

        model.addAttribute("searchtotalPage", searchtotalPage);
        model.addAttribute("roletypeList", listOfRole);
        model.addAttribute("SEARCH_ROLE", searchRole);
        model.addAttribute("searchcurrentPage", page);
        
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
        Set<RoleType> roletype = roleTypeService.loadAllRoleType();
        List<RoleType> roletypeList = roletype.stream().collect(Collectors.toList());
        model.addAttribute("roletypeList", roletypeList);
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

        //remove irregular spaces
        String rName = form.getRolename().replaceAll("\\s+", " ");
        
        // assign all roletypes to roletypeList model attribute
        Set<RoleType> roletype = roleTypeService.loadAllRoleType();
        List<RoleType> roletypeList = roletype.stream().collect(Collectors.toList());
        model.addAttribute("roletypeList", roletypeList);
        Set<RoleType> roleSize = roleTypeService.findRoleTypeByName(form.getRolename().toLowerCase());

        if (roleSize == null) {
            RoleType roleDetails = new RoleType.Builder(rName.trim(), form.getRoledesc()).build();
            roleTypeService.createRoleType(roleDetails);
        } else {
            model.addAttribute("create");
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
    @GetMapping("/update/{roleIdUpdate}")
    public String showUpdateRoleTypeForm(@PathVariable("roleIdUpdate") Long id, RoleTypeForm form, Model model) {
        
        // assign all roletypes to roletypeList model attribute
        Set<RoleType> roletype = roleTypeService.loadAllRoleType();
        List<RoleType> roletypeList = roletype.stream().collect(Collectors.toList());
        model.addAttribute("roletypeList", roletypeList);
        model.addAttribute("roleIdUpdate", id);
        
        // Set Value for RoleType Object
        RoleType role = roleTypeService.findRoleById(id);
        
        // Set Value for RoleTypeForm
        form.setId(role.getId());
        form.setRolename(role.getRolename());
        form.setRoledesc(role.getRoledesc());
        model.addAttribute("updateRoleTypeForm", form);

       // return "roletype-management/roleTypeUpdate";
        return "redirect:/roletype/load/1?roleIdUpdate= " + id + "#confirmUpdateModal";
    }

    /**
     * Update the role type. Method = POST
     * 
     * @param id roleId
     * @param form RoleTypeForm
     * @param model Model
     * @param redirectAttributes RedirectAttributes
     * @return RoleTypeForm and view
     */
    @PostMapping("/update/{roleId}")
    public String submitUpdateRoleTypeForm(@RequestParam("id") Long id, RoleTypeForm form, Model model, RedirectAttributes redirectAttributes) {

      //remove irregular spaces
        String rName = form.getRolename().replaceAll("\\s+", " ");
        
        // assign all roletypes to roletypeList model attribute
        Set<RoleType> roletype = roleTypeService.loadAllRoleType();
        List<RoleType> roletypeList = roletype.stream().collect(Collectors.toList());
        model.addAttribute("roletypeList", roletypeList);
        model.addAttribute("roleId", id);
        boolean isRoleExisting = roleTypeService.findIfRoleNameExists(form.getRolename().toLowerCase(), id);

        // Check if Role Type is already existing in the table
        if (isRoleExisting) {
            form.setId(id);
            model.addAttribute("updateRoleTypeForm", form);
            return "redirect:/roletype/load/1";
        } else {
            RoleType updatedRoleType = new RoleType.Builder(rName.trim(), form.getRoledesc()).build();
            roleTypeService.updateRoleType(id, updatedRoleType);
            redirectAttributes.addFlashAttribute("message",
                    "You have successfully updated this role type");

            // set 2 seconds delay
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        return "redirect:/roletype/load/1#successModal";
    }
}