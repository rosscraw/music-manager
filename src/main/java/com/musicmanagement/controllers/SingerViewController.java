package com.musicmanagement.controllers;

import java.util.List;

import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.services.SingerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String listSingers(Model model) {
        // List<Singer> singerList = singerService.listAllSingers();
        // model.addAttribute("singersList", singerList);
        return viewPage(model, 1);
    }

    /**
     * 
     * @param model
     * @return the new singer html file to display.
     */
    @RequestMapping("singer-list/new-singer")
    public String showNewProductPage(Model model) {
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
        singerService.saveSinger(singer);
        ;

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
        mav.addObject("singer", singer);
         
        return mav;
    }

    /**
     * Page numbering.
     * @param model
     * @param pageNum
     * @return singerlist page.
     */
    @RequestMapping("singer-list/page/{pageNum}")
    public String viewPage(Model model, @PathVariable(name = "pageNum") int pageNum) {

        Page<Singer> page = singerService.listAll(pageNum);

        List<Singer> listSinger = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("singersList", listSinger);

        return "singer/singerlist";
    }
}
