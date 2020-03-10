package com.lagou.edu.controller;

import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.login.LoginSession;
import com.lagou.edu.pojo.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    /**
     * Spring容器和SpringMVC容器是有层次的（父子容器）
     * Spring容器：service对象+dao对象
     * SpringMVC容器：controller对象，，，，可以引用到Spring容器中的对象
     */


    @Autowired
    private ResumeDao resumeDao;

    @RequestMapping(path="/selectAll",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String  selectAll() throws Exception {

        List<Resume> resumes = resumeDao.findAll();
        StringBuilder sb = new StringBuilder();
        for(Resume resume:resumes){
            sb.append("  <tr>\n");

            sb.append("<td>");
            sb.append(resume.getId());
            sb.append("</td>\n");

            sb.append("<td>");
            sb.append(resume.getAddress());
            sb.append("</td>\n");

            sb.append("<td>");
            sb.append(resume.getName());
            sb.append("</td>\n");

            sb.append("<td>");
            sb.append(resume.getPhone());
            sb.append("</td>\n");

            sb.append("<td>");
            sb.append("<a href='/resume/update?id="+resume.getId()+"'>更新</a>");
            sb.append("</td>\n");

            sb.append("<td>");
            sb.append("<a href='/resume/delete?id="+resume.getId()+"'>删除</a>");
            sb.append("</td>\n");


            sb.append("  </tr>\n");
        }

        String html="<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
//                "<meta http-equiv=\"Refresh\" content=\"10;url=/resume/selectAll\" />" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"+
                "<title>简历列表</title><body>\n" +
                "<a href='/resume/create'>创建</a>\n" +
                "\n" +
                "<form>\n" +
                "<table border=\"1\">\n" +sb.toString()+
                "</table>"+
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        return html;
    }
//
//
    @RequestMapping(path="/create",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String  create() throws Exception {
    StringBuilder sb = new StringBuilder();

    String input = "<table class=\"lp-login\">\n" +
            "        <tr>\n" +
            "            <td align=\"right\"><span>地址</span></td>\n" +
            "            <td align=\"center\">\n" +
            "                <input type=\"text\" name=\"address\"  ></input>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td align=\"right\"><span>名字</span></td>\n" +
            "            <td align=\"center\">\n" +
            "                <input type=\"text\" name=\"name\"  ></input>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td align=\"right\"><span>电话</span></td>\n" +
            "            <td align=\"center\">\n" +
            "                <input type=\"text\" name=\"mobile\" ></input>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "    </table>";
    String html="<!doctype html>\n" +
            "<html>\n" +
            "<head>\n" +
//                "<meta http-equiv=\"Refresh\" content=\"10;url=/resume/selectAll\" />" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"+
            "<title>简历创建</title><body>\n" +
            "\n" +
            "\n" +
            "<form action='/resume/doUpdate' method='post' accept-charset=\"UTF-8\" onsubmit=\"document.charset='UTF-8'\">\n" +input+
            "<input type=\"submit\" value=\"提交\"/>"+
            "</form>\n" +
            "\n" +
            "</body>\n" +
            "</html>";


    return html;
}

    @RequestMapping(path="/update",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String  update(Long id ) throws Exception {
        Optional<Resume> optionalResume = resumeDao.findById(id);
        Resume resume = optionalResume.get();
        StringBuilder sb = new StringBuilder();

        String input = "<table class=\"lp-login\">\n" +
                "        <tr>\n" +
                "            <td align=\"right\"><span>id</span></td>\n" +
                "            <td align=\"center\">\n" +
                "                <input type=\"text\" name=\"id\" value=\""+resume.getId()+"\" ></input>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"right\"><span>地址</span></td>\n" +
                "            <td align=\"center\">\n" +
                "                <input type=\"text\" name=\"address\" value=\""+resume.getAddress()+"\" ></input>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"right\"><span>名字</span></td>\n" +
                "            <td align=\"center\">\n" +
                "                <input type=\"text\" name=\"name\" value=\""+resume.getName()+"\" ></input>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"right\"><span>电话</span></td>\n" +
                "            <td align=\"center\">\n" +
                "                <input type=\"text\" name=\"mobile\" value=\""+resume.getPhone()+"\" ></input>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>";
        String html="<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
//                "<meta http-equiv=\"Refresh\" content=\"10;url=/resume/selectAll\" />" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"+
                "<title>简历列表</title><body>\n" +
                "\n" +
                "\n" +
                "<form action='/resume/doUpdate' method='post' accept-charset=\"UTF-8\" onsubmit=\"document.charset='UTF-8'\">\n" +input+
                "<input type=\"submit\" value=\"提交\"/>"+
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>";


        return html;
    }

    @RequestMapping(path="/doUpdate",produces = "text/html; charset=utf-8")
    @ResponseBody
    public void  doUpdate(HttpServletRequest request , String name, String address, String mobile, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Resume resume = new Resume();
        if(id!=null&&!id.equals("")){

            resume.setId(Long.valueOf(id));
        }
        resume.setName(name);
        resume.setAddress(address);
        resume.setPhone(mobile);
        Resume optional = resumeDao.save(resume);
        response.sendRedirect("/resume/selectAll");
//        return "redirect:./resume/selectAll";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void  delete(Long id, HttpServletResponse response) throws Exception {
        resumeDao.deleteById(id);
        response.sendRedirect("/resume/selectAll");

    }

    @RequestMapping("/login")
    @ResponseBody
    public void  login(String username,String password, HttpServletResponse response) throws Exception {

        if(username.equals("admin")&&password.equals("admin")){
            LoginSession.getLoginSession().putLoginSession();
        }
        response.sendRedirect("/resume/selectAll");

    }

    @RequestMapping(path="/loginPage",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String  loginPage() throws Exception {
        StringBuilder sb = new StringBuilder();

        String input = "<table class=\"lp-login\">\n" +
                "        <tr>\n" +
                "            <td align=\"right\"><span>用户名</span></td>\n" +
                "            <td align=\"center\">\n" +
                "                <input type=\"text\" name=\"username\"  ></input>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"right\"><span>密码</span></td>\n" +
                "            <td align=\"center\">\n" +
                "                <input type=\"text\" name=\"password\"  ></input>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>";
        String html="<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
//                "<meta http-equiv=\"Refresh\" content=\"10;url=/resume/selectAll\" />" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"+
                "<title>简历创建</title><body>\n" +
                "\n" +
                "\n" +
                "<form action='/resume/login' method='post' accept-charset=\"UTF-8\" onsubmit=\"document.charset='UTF-8'\">\n" +input+
                "<input type=\"submit\" value=\"登录\"/>"+
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>";


        return html;
    }


}
