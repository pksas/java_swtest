package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("185.13.112.126");
    assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }

  @Test
  public void testInvalidIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("57.55.112.xxx");
    assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }
}
