package bedon.com.controllers;

import bedon.com.DAO.MyFileDAO;
import bedon.com.DAO.UserDAO;
import bedon.com.entities.MyFile;
import bedon.com.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/")
@Scope("session")
public class MainController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MyFileDAO myFileDAO;

    @Autowired
    private User user;

    @RequestMapping(value = "/")
    public ModelAndView onIndex(Model model) {
        if (user.isLoggedIn()) {
            model.addAttribute("user", user.getLogin());
            return new ModelAndView("index", "myfile", myFileDAO.list(user));
        }
        return new ModelAndView("login");
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "id") int id,
                               Model model) {
        myFileDAO.delete(id);
        model.addAttribute("user", user.getLogin());
        return new ModelAndView("index", "myfile", myFileDAO.list(user));
    }

    @RequestMapping("/image/{file_id}")
    public void getFile(HttpServletResponse response, @PathVariable("file_id") int fileId) {
        try {
            MyFile file = myFileDAO.getFile(fileId);

            File f = new File(user.getPath() + "\\" +  file.getOriginalName());

            byte[] fileBody = new byte[(int)f.length()];

            FileInputStream in = new FileInputStream(f);
            in.read(fileBody);

            response.getOutputStream().write(fileBody);

            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping("/download/{file_id}")
    public void downloadFile(HttpServletResponse response, @PathVariable("file_id") int fileId) {
        try {
            MyFile file = myFileDAO.getFile(fileId);

            File f = new File(user.getPath() + "\\" +  file.getOriginalName());

            byte[] fileBody = new byte[(int)f.length()];

            FileInputStream in = new FileInputStream(f);
            in.read(fileBody);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getOriginalName());
            response.getOutputStream().write(fileBody);

            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(value = "pattern") String pattern,
                               Model model) {
        if (pattern.equals("")) {
            model.addAttribute("user", user.getLogin());
            return new ModelAndView("index", "myfile", myFileDAO.list(user));
        }
        model.addAttribute("user", user.getLogin());
        return new ModelAndView("index", "myfile", myFileDAO.getFileByName(user, pattern));
    }

    @RequestMapping(value = "add-file", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam(value = "name") String name,
                            @RequestParam(value = "file") MultipartFile file,
                            Model model) {
        if (file.isEmpty()) {
            return null;
        }

        if (name.equals("")) {
            name = file.getOriginalFilename();
        }
        try {

            MyFile myFile = new MyFile(name, file.getOriginalFilename(), user.getPath() + "/" + file.getOriginalFilename());
            myFile.setUser(user);

            byte[] buff = file.getBytes();


            OutputStream out = new FileOutputStream(myFile.getPath());
            try {
                out.write(buff);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            myFileDAO.add(myFile);
            model.addAttribute("user", user.getLogin());
            return new ModelAndView("index", "myfile", myFileDAO.list(user));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user.getLogin());
            return new ModelAndView("index", "myfile", myFileDAO.list(user));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "login") String login,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "email") String email,
                           Model model) {
        User user = new User(login, password, email);
        if (userDAO.checkLogin(user.getLogin())) {
            File path = new File("E:\\JavaProject\\WebApp\\storage\\" + user.getLogin());
            path.mkdirs();
            user.setPath("E:/JavaProject/WebApp/storage/" + user.getLogin());
            userDAO.addUser(user);
            model.addAttribute("regError", "Operation success)))");
            return "login";
        } else {
            model.addAttribute("regError", "Something wrong(((");
            return "registration";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView logIn(@RequestParam(value = "login") String login,
                              @RequestParam(value = "password") String password,
                              Model model) {
        user.setLogin(login);
        user.setPassword(password);
        if (userDAO.checkUser(user)) {
            user = userDAO.getUser(login);
            user.setLoggedIn(true);
            model.addAttribute("user", user.getLogin());
            return new ModelAndView("index", "myfile", myFileDAO.list(user));
        }
        model.addAttribute("logError", "Wrong login/password");
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/index")
    public ModelAndView files() {
        if (!user.isLoggedIn()) {
            return new ModelAndView("login");
        }

        ModelAndView modelAndView = new ModelAndView("index", "myfile", myFileDAO.list(user));

        modelAndView.addObject("user", user.getLogin());

        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        user.setLoggedIn(false);
        user = new User();
        return "login";
    }
}
