package com.musicmanagement.controllers;

import java.sql.Date;
import java.util.List;

import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.services.SingerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
/**
 * Controller to handle views relating to the Singer Page. Includes viewing a
 * table of singers, being able to add a new singer, updating a singer's
 * details.
 */
public class SingerViewController {

    @Autowired
    private SingerService singerService;

    /**
     * 
     * @param model
     * @return the singer list html file to display.
     */
    @RequestMapping("singer-list")
    public String listSingers(Model model, @Param("search") String search) {
        // List<Singer> singerList = singerService.listAllSingers();
        // model.addAttribute("singersList", singerList);
        return viewPage(model, search, 1, "name", "asc");
    }

    /**
     * 
     * @param model
     * @return the new singer html file to display.
     */
    @RequestMapping("singer-list/new-singer")
    public String showNewSingerPage(Model model) {
        Singer singer = new Singer();
        model.addAttribute("singer", singer);

        return "singer/new-singer";
    }

    /**
     * @param singer the singer to add to the database
     * @return the singer list html page to display.
     */
    @RequestMapping(value = "/save-singer", method = RequestMethod.POST)
    public String saveSinger(@ModelAttribute("singer") Singer singer) {
        singer.setCompany(singer.getCompany().trim().toUpperCase()).setName(singer.getName().trim().toUpperCase())
                .setSex(singer.getSex().trim().toUpperCase());

        // int dob = singer.getDob();
        // int year = dob / 10000 - 1900;
        // int month = (dob % 10000) / 100 - 1;
        // int day = dob % 100;
        // singer.setDate(new Date(year, month, day));
        int dob = 19 * 1000000 + singer.getDate().getYear() * 10000 + (singer.getDate().getMonth() + 1) * 100 + singer.getDate().getDate();
        singer.setDob(dob);
        singerService.saveSinger(singer);

        return "redirect:/singer-list";
    }

    /**
     * 
     * @param id the singer's id.
     * @return edit page for the singer.
     */
    @RequestMapping("singer-list/edit-singer/{id}")
    public ModelAndView editSinger(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("singer/edit-singer");
        Singer singer = singerService.getSinger(id);
        int dob = 19 * 1000000 + singer.getDate().getYear() * 10000 + (singer.getDate().getMonth() + 1) * 100 + singer.getDate().getDate();
        singer.setDob(dob);
        mav.addObject("singer", singer);

        return mav;
    }

    /**
     * Page numbering.
     * 
     * @param model
     * @param pageNum
     * @return singerlist page.
     */
    @RequestMapping("singer-list/page/{pageNum}")
    public String viewPage(Model model, @Param("search") String search, @PathVariable(name = "pageNum") int pageNum,
            @Param("sortField") String sortField, @Param("sortDir") String sortDir) {

        Page<Singer> page = singerService.listAll(pageNum, sortField, sortDir, search);

        List<Singer> listSinger = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("search", search);

        model.addAttribute("singersList", listSinger);

        return "singer/singerlist";
    }

    /**
     * Delete a singer.
     * 
     * @param id the singer's id to be deleted.
     * @return the signer list page.
     */
    @RequestMapping("singer-list/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        singerService.deleteSinger(id);
        return "redirect:/singer-list";
    }
}
