package com.musicmanagement.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.musicmanagement.datatypes.Album;
import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.services.AlbumService;
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
 * Controller to handle views relating to the Album Page. Includes viewing a
 * table of albums, being able to add a new album, updating a album's details.
 */
public class AlbumViewController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SingerService singerService;

    /**
     * Method to set a year range for dropdown year selection
     * 
     * @param earliest the earliest year in the drop down
     * @param latest the latest year in the drop down
     * @return list of years for dropdown selection
     */
    public static List<Integer> yearRange(int earliest, int latest) {
        List<Integer> list = new ArrayList<Integer>();
        while (earliest <= latest) {
            list.add(latest++);
        }
        return list;
    }

    /**
     * @param model the model to add attributes to.
     * @return the album list html file to display.
     */
    @RequestMapping("album-list")
    public String listAlbums(Model model, @Param("search") String search) {
        // List<Album> albumList = albumService.listAllAlbums();
        // model.addAttribute("albumsList", albumList);
        return viewPage(model, search, 1, "title", "asc");
    }

    /**
     * @param model the model to add attribute to.
     * @return the new album html file to display.
     */
    @RequestMapping("album-list/new-album")
    public String showNewAlbumPage(Model model) {
        List<Integer> years = yearRange(1950, 2020);
        List<Singer> singers = singerService.listAllSingers();
        singers.sort(Comparator.comparing(Singer::getName));
        Album album = new Album();
        model.addAttribute("album", album);
        model.addAttribute("singers", singers);
        model.addAttribute("years", years);

        return "album/new-album";
    }

    /**
     * @param album the album to add to the database
     * @return the album list html page to display.
     */
    @RequestMapping(value = "/save-album", method = RequestMethod.POST)
    public String saveAlbum(@ModelAttribute("album") Album album) {
        album.setCompany(album.getCompany().trim().toUpperCase()).setName(album.getName().trim().toUpperCase())
                .setSinger(album.getSinger().trim().toUpperCase());
        albumService.saveAlbum(album);

        return "redirect:/album-list";
    }

    /**
     * @param id the album's id.
     * @return edit page for the album.
     */
    @RequestMapping("album-list/edit-album/{id}")
    public ModelAndView editAlbum(@PathVariable(name = "id") int id) {
        List<Integer> years = yearRange(1950, 2020);
        List<Singer> singers = singerService.listAllSingers();
        singers.sort(Comparator.comparing(Singer::getName));
        ModelAndView mav = new ModelAndView("album/edit-album");
        Album album = albumService.getAlbum(id);
        mav.addObject("album", album);
        mav.addObject("singers", singers);
        mav.addObject("years", years);

        return mav;
    }

    /**
     * Maps the request dependent upon page number and search term.
     * 
     * @param search  the search term.
     * @param model   the model to add attributes to.
     * @param pageNum the page number.
     * @return albumlist page.
     */
    @RequestMapping("album-list/page/{pageNum}")
    public String viewPage(Model model, @Param("search") String search, @PathVariable(name = "pageNum") int pageNum,
            @Param("sortField") String sortField, @Param("sortDir") String sortDir) {

        Page<Album> page = albumService.listAll(pageNum, sortField, sortDir, search);

        List<Album> listAlbum = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("search", search);

        model.addAttribute("albumsList", listAlbum);

        return "album/albumlist";
    }

    /**
     * Delete an album.
     * 
     * @param id the album's id to be deleted.
     * @return the signer list page.
     */
    @RequestMapping("album-list/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        albumService.deleteAlbum(id);
        return "redirect:/album-list";
    }
}
