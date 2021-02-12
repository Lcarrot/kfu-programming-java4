package ru.kpfu.springcontrollers.controllers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tests")
public class DefaultController {

  @RequestMapping
  public String index(ModelMap map) {
    map.put("viewVariable", "Index action");
    return "tests";
  }

  @RequestMapping("/s")
  public String simple(ModelMap map) {
    map.put("viewVariable", "Just simple action");
    return "tests";
  }

  @RequestMapping({"/s1", "/s2"})
  public String simpleMultiple(ModelMap map) {
    map.put("viewVariable", "Just simple action for 2 addresses");
    return "tests";
  }

  @RequestMapping("/rb")
  @ResponseBody
  public String responseBody() {
    return "The only text on a page is this text - @ResponseBody annotation.";
  }

  @RequestMapping("/writer")
  public void writer(java.io.Writer writer) throws IOException {
    writer.write("This is plain/text done with java.io.Writer");
  }

  @RequestMapping("/rs")
  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  public String responseStatus(ModelMap map) {
    map.put("viewVariable", "What do you want from me? I'm a teapot!");
    return "tests";
  }

  @RequestMapping(
          value = "/rmb",
          method = RequestMethod.GET,
          headers = "User-Agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36"
  )
  public String requestMappingBunch(ModelMap map, HttpServletRequest request) {
    map.put("viewVariable", request.getHeader("User-Agent"));
    return "tests";
  }

  @RequestMapping("/rp")
  public String requestParam(@RequestParam String param, ModelMap map) {
    map.put("viewVariable", "Request parameter:<br>" + param);
    return "tests";
  }

  @RequestMapping("/rpb")
  public String requestParamBanch(
          @RequestParam(
                  value = "special_name",
                  required = false,
                  defaultValue = "Default value"
          ) String param,
          ModelMap map
  ) {
    map.put("viewVariable", "Request parameter:<br>" + param);
    return "tests";
  }

  @RequestMapping("/pv/{param}")
  public String pathVariable(@PathVariable String param, ModelMap map) {
    map.put("viewVariable", "Path variable:<br>" + param);
    return "tests";
  }

  @RequestMapping("/pvb/{param_special_name}/{param2}/path")
  public String pathVariableBunch(
          @PathVariable(
                  value = "param_special_name"
          ) String param,
          @PathVariable String param2,
          ModelMap map
  ) {
    map.put("viewVariable", "Path variable:<br>" + param);
    return "tests";
  }

  @RequestMapping("/rh")
  public String requestHeader(@RequestHeader Map<String, String> headers, ModelMap map) {
    StringBuilder builder = new StringBuilder();
    headers.forEach((key, value) -> builder.append("<p>Header -- ").append(key).append("-- has value --").append(value).append(" --").append("</p>"));
    map.put("viewVariable", builder);
    return "tests";
  }
}
