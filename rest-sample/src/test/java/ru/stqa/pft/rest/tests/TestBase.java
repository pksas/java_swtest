package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;

public class TestBase {
  boolean isIssueOpen(int issueId) {
    String json = RestAssured.get(String.format("http://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issuesSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    boolean openFlg = issuesSet.iterator().next().getState_name().equals("Open");
    return openFlg;
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
