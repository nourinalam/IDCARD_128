/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idcard.pws.idcard;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Controller
public class controller {
    @RequestMapping("/idcard")
    public String getData(@RequestParam("name") String nama,
                          @RequestParam("tanggal")@DateTimeFormat(pattern ="yyyy-MM-dd") Date date,
                          @RequestParam("gambar") MultipartFile file,
                          Model model) throws IOException{
        
        SimpleDateFormat sul = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String tanggal = sul.format(date);
        
        String blob = Base64.encodeBase64String(file.getBytes());
        String gambar = "data:image/jpeg;base64,".concat(blob);
        
        model.addAttribute("name", nama);
        model.addAttribute("date", tanggal);
        model.addAttribute("image", gambar);
                
        return "view";
    }
            
}