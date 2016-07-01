package citrus_server_mocking;
// package io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest;
//
/// **
// * TODO shuber This type ...
// *
// * @author shuber
// * @since dev
// */
// import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
// import static com.github.tomakehurst.wiremock.client.WireMock.containing;
// import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
// import static com.github.tomakehurst.wiremock.client.WireMock.get;
// import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
// import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
// import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
// import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
// import static com.github.tomakehurst.wiremock.client.WireMock.verify;
//
// import java.io.File;
// import java.io.IOException;
// import java.util.List;
//
// import javax.inject.Inject;
//
// import org.apache.commons.codec.binary.Base64;
// import org.apache.commons.io.FileUtils;
// import org.flywaydb.core.Flyway;
// import org.json.CDL;
// import org.json.JSONArray;
// import org.json.JSONException;
// import org.json.JSONObject;
// import org.junit.Rule;
// import org.junit.Test;
// import org.springframework.batch.core.BatchStatus;
// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.JobExecution;
// import org.springframework.batch.core.JobParameters;
// import org.springframework.batch.core.JobParametersBuilder;
// import org.springframework.boot.test.SpringApplicationConfiguration;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.web.client.ResourceAccessException;
// import org.springframework.web.client.RestTemplate;
//
// import com.github.tomakehurst.wiremock.client.WireMock;
// import com.github.tomakehurst.wiremock.http.Fault;
// import com.github.tomakehurst.wiremock.junit.WireMockRule;
//
// import io.oasp.gastronomy.restaurant.SpringBootBatchApp;
// import io.oasp.gastronomy.restaurant.general.common.AbstractSpringBatchIntegrationTest;
// import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
// import io.oasp.gastronomy.restaurant.offermanagement.common.api.datatype.OfferState;
// import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
// import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
//
/// **
// * This test demands a list of offers from an external server which is mocked with WireMock. The HTTP request and
// * response is stubbed to simulate the use case. For the received list of offers a batch job is initialized to save or
// * update the new data.
// *
// * @author sroeger
// */
//
// @SpringApplicationConfiguration(classes = { SpringBootBatchApp.class }, locations = {
// "classpath:config/app/batch/beans-offerimport.xml" })
// @WebAppConfiguration
// @TestPropertySource(properties = { "flyway.locations=db/migration" })
// public class WireMockOfferImport extends AbstractSpringBatchIntegrationTest {
//
// @Inject
// private Job offerImportJob;
//
// @Inject
// private Offermanagement offermanagement;
//
// @Inject
// private Flyway flyway;
//
// // port of mocked server
// private int port = 8089;
//
// private RestTemplate template = new RestTemplate();
//
// // start mocked server on port {@Link port}. This is done before spring boot is started
// @Rule
// public WireMockRule wireMockRule = new WireMockRule(this.port);
//
// @Test
// public void requestDataAndDeleteAndInsertDataTest() throws Exception {
//
// this.flyway.clean();
// this.flyway.migrate();
// cleanOffers();
//
// // WireMock test
// // given
// stubFor(get(urlEqualTo("/new/offers")).withHeader("Accept", equalTo("application/json"))
// .withHeader("Authorization", containing("Basic")).willReturn(aResponse().withStatus(200).withFixedDelay(1000)
// .withHeader("Content-Type", "application/json").withBodyFile("/wireMockTest/jsonBodyFile.json")));
// WireMock.addRequestProcessingDelay(300);
// HttpEntity<String> getRequest = new HttpEntity<String>(getHeaders());
//
// // when
// String url = generateBaseUrl() + "/new/offers";
// ResponseEntity<String> getResponse = this.template.exchange(url, HttpMethod.GET, getRequest, String.class);
//
// // then
// String getResponseJson = getResponse.getBody();
// verify(getRequestedFor(urlMatching("/new/offers")).withHeader("Accept", equalTo("application/json")));
// jsonToCsv(getResponseJson);
//
// // batch job test with requested data
// // given
// JobParametersBuilder jobParameterBuilder = new JobParametersBuilder();
// jobParameterBuilder.addString("pathToFile", "file:tmp/fromJSON.csv");
// JobParameters jobParameters = jobParameterBuilder.toJobParameters();
//
// // when
// JobExecution jobExecution = getJobLauncherTestUtils(this.offerImportJob).launchJob(jobParameters);
//
// // then
// assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
//
// // - imported data (there are 4 offers in setup data, where 1 is not valid and gets skipped)
// List<OfferEto> allOffers = this.offermanagement.findAllOffers();
// assertThat(allOffers).hasSize(3);
//
// // - exemplary offer
// OfferEto offer = allOffers.get(0);
// assertThat(offer.getName()).isEqualTo("Leckeres-Menu");
// assertThat(offer.getDescription()).isEqualTo("Description of Leckeres-Menu");
// assertThat(offer.getPrice()).isEqualTo(new Money(15.99));
// assertThat(offer.getState()).isEqualTo(OfferState.NORMAL);
//
// }
//
// @Test
// public void faultTest() {
//
// // given
// stubFor(get(urlEqualTo("/fault")).willReturn(aResponse().withFault(Fault.MALFORMED_RESPONSE_CHUNK)));
// HttpEntity<String> getRequest = new HttpEntity<String>(getHeaders());
//
// // when
// String url = generateBaseUrl() + "/fault";
// ResponseEntity<String> getResponse = null;
// Exception exception = null;
// try {
// getResponse = this.template.exchange(url, HttpMethod.GET, getRequest, String.class);
// } catch (Exception e) {
// exception = e;
// }
//
// // then
// assertThat(getResponse).isNull();
// assertThat(exception).isInstanceOf(ResourceAccessException.class);
// verify(getRequestedFor(urlMatching("/fault")));
// }
//
// private HttpHeaders getHeaders() {
//
// String plainCreds = "chief" + ":" + "chief";
// byte[] plainCredsBytes = plainCreds.getBytes();
// byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
// String base64Creds = new String(base64CredsBytes);
// HttpHeaders headers = new HttpHeaders();
// headers.add(HttpHeaders.AUTHORIZATION, "Basic " + base64Creds);
// headers.add(HttpHeaders.ACCEPT, "application/json");
//
// return headers;
// }
//
// private String generateBaseUrl() {
//
// return "http://localhost:" + this.port;
// }
//
// private String jsonToCsv(String getResponseJson) throws IOException {
//
// JSONObject output;
// String csv = null;
// try {
// output = new JSONObject(getResponseJson);
//
// JSONArray docs = output.getJSONArray("Speisekarte");
// File file = new File("./tmp/fromJSON.csv");
// csv = CDL.toString(docs);
// FileUtils.writeStringToFile(file, csv);
//
// } catch (JSONException e) {
// e.printStackTrace();
// }
// return csv;
//
// }
//
// private void cleanOffers() {
//
// for (OfferEto offer : this.offermanagement.findAllOffers()) {
// this.offermanagement.deleteOffer(offer.getId());
// }
//
// }
// }
