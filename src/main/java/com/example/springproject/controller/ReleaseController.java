package com.example.springproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springproject.entity.Release;
import com.example.springproject.entity.Connection3;
import com.example.springproject.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/release")
public class ReleaseController {

  @Autowired
  private ReleaseService releaseService;

  @GetMapping("/store-releases")
  public String storeReleases(@RequestParam String url) throws IOException {
    List<Release> releases = getRawJson(url);
    if (releases.size() != 0) {
      releaseService.insertReleases(releases);
      return "Releases stored";
    }
    return "No Release";
  }

  @GetMapping("/get-releases")
  public int getReleases() {
    return releaseService.getReleases();
  }

  @GetMapping("/get-commits-preRelease")
  public List<Connection3> getCommitsBeforeReleases() {
    List<Connection3> initialList = releaseService.getCommitsBeforeReleases();
    List<Connection3> resultList = new ArrayList<>();
    for (int i = 0; i < initialList.size(); i++) {
      Connection3 connection3;
      if (i == 0) {
        connection3 = new Connection3();
        connection3.setReleaseId(initialList.get(i).getReleaseId());
        connection3.setCommitNum(initialList.get(i).getCommitNum());
      } else {
        connection3 = new Connection3();
        connection3.setCommitNum(
            initialList.get(i).getCommitNum() - initialList.get(i - 1).getCommitNum());
        connection3.setReleaseId(initialList.get(i).getReleaseId());
      }
      resultList.add(connection3);
    }
    return resultList;
  }

  public List<Release> getRawJson(String url) throws IOException {
    int pageNum = 0;
    String rawJson = "";
    List<Release> releases = new ArrayList<>();

    while (!rawJson.equals("[]")) {
      pageNum++;
      String urlWithPage = url + "&page=" + pageNum;

      URL restURL = new URL(urlWithPage);

      HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();

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
        Release release = new Release();
        release.setId(jsonObject.getIntValue("id"));
        release.setPublish_time(jsonObject.getTimestamp("published_at"));
        releases.add(release);
      }
    }
    System.out.println(releases.size());
    return releases;
  }
}
