package com.example.springproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springproject.entity.Commit;
import com.example.springproject.entity.Connection1;
import com.example.springproject.entity.Connection2;
import com.example.springproject.service.CommitService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * javadoc.
 */
@CrossOrigin
@RestController
@RequestMapping("/commit")
public class CommitController {

  /**
   * javadoc.
   */
  @Autowired
  private CommitService commitService;

  /**
   *
   * @param url
   * @return
   * @throws IOException
   */
  @GetMapping("/store-commits")
  public String storeCommits(@RequestParam String url) throws IOException {
    List<Commit> commits = getRawJson(url);
    if (commits.size() != 0) {
      commitService.insertCommits(getRawJson(url));
      return "Commits stored";
    }
    return "No commit";
  }

  @GetMapping("/get-date-commitNum")
  public List<Connection1> getCommitNumByTime() {
    return commitService.getCommitNumByTime();
  }

  /**
   *
   * @return fsadfs
   */
  @GetMapping("/get-developer-commitNum")
  public List<Connection2> getCommitNumByDeveloper() {
    return commitService.getCommitNumByDeveloper();
  }

  public List<Commit> getRawJson(String url) throws IOException {
    int pageNum = 0;
    String rawJson = "";
    List<Commit> commits = new ArrayList<>();

    while (!rawJson.equals("[]")) {
      pageNum++;
      String urlWithPage = url + "&page=" + pageNum;

      URL restUrl = new URL(urlWithPage);

      HttpURLConnection conn = (HttpURLConnection) restUrl.openConnection();

      conn.setRequestMethod("GET"); // POST GET PUT DELETE
      // Bearer?????????????????????github token????????????????????????
      conn.setRequestProperty("authorization", "Bearer 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");
      conn.setRequestProperty("Accept", "vnd.github+json");

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      rawJson = br.readLine();
      br.close();
      JSONArray jsonArray = JSON.parseArray(rawJson);

      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        Commit commit = new Commit();
        commit.setSha(jsonObject.getString("sha"));
        commit.setDeveloper(jsonObject.getJSONObject("commit")
            .getJSONObject("author").getString("name"));
        commit.setCommit_date(jsonObject.getJSONObject("commit")
            .getJSONObject("author").getSqlDate("date"));
        commit.setCommit_time(jsonObject.getJSONObject("commit")
            .getJSONObject("author").getTimestamp("date"));
        commits.add(commit);
      }
    }
    System.out.println(commits.size());
    return commits;
  }
}
