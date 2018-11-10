package com.test.scraper.parser;

import com.test.scraper.TestApplicationConfiguration;
import com.test.scraper.bean.ItemBean;
import com.test.scraper.exception.MalformedDataException;
import com.test.scraper.utility.Fetcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ItemParserTest {

    @Mock
    Fetcher fetcher;

    @InjectMocks
    private
    ItemParser itemParser = new ItemParserImpl();


    @Test
    public void shouldReturnCompleteItem() throws Exception {
        // Given a valid item section
        Document givenItemHtml = getItemHtml();
        when(fetcher.fetchDocument(anyString())).thenReturn(getDescriptionHtml());

        // When parser parses it
        ItemBean completeItem = itemParser.extractCompleteItem(givenItemHtml);

        // Then we see item title, unit price, energy and description
        assertThat(completeItem.getTitle(), is("Sainsbury's Strawberries 400g"));
        assertThat(completeItem.getUnitPrice(), is(1.75));
        assertThat(completeItem.getDescription(), is("by Sainsbury's raspberries"));
        assertThat(completeItem.getKcalPer100g(), is(32));
    }

    @Test
    public void shouldReturnItemWithTitleAndPrice() throws MalformedDataException {
        // Given a valid item section
        Document givenItemHtml = getItemHtml();

        // When parser parses it
        ItemBean item = itemParser.extractItem(givenItemHtml);

        // Then we see item title and unit price
        assertThat(item.getTitle(), is("Sainsbury's Strawberries 400g"));
        assertThat(item.getUnitPrice(), is(1.75));
    }

    @Test(expected = MalformedDataException.class)
    public void shouldNotReturnItem() throws MalformedDataException {
        // Given a valid item section
        // When parser parses it
        ItemBean item = itemParser.extractItem(getInvalidItemHtml());

        // Then we expect exception
    }

    @Test
    public void shouldReturnItemWithDescriptionAndNutrition() throws MalformedDataException {
        // Given a valid item description page and an item
        Document givenItemDescription = getDescriptionHtml();
        ItemBean givenItem = new ItemBean();

        // When parser parses it
        itemParser.extractDescriptionAndNutritionIntoItem(givenItemDescription, givenItem);

        // Then we see item populated with description and energy
        assertThat(givenItem.getDescription(), is("by Sainsbury's raspberries"));
        assertThat(givenItem.getKcalPer100g(), is(32));
    }

    @Test(expected = MalformedDataException.class)
    public void shouldNotReturnItemDescriptionAndNutrition() throws MalformedDataException {
        // Given an invalid item description page and an item
        Document givenItemDescriptionHtml = getInvalidDescriptionHtml();
        ItemBean givenItem = ItemBean.builder().build();

        // When parser parses it
        itemParser.extractDescriptionAndNutritionIntoItem(givenItemDescriptionHtml, givenItem);

        // Then we expect exception
    }

    private Document getInvalidDescriptionHtml() {
        return Jsoup.parse("Invalid item description html");
    }

    private Document getInvalidItemHtml() {
        return Jsoup.parse("Invalid item html");
    }

    private Document getItemHtml() {
        String html = "<li class=\"gridItem\">\n" +
                "\t                                <!-- BEGIN CatalogEntryThumbnailDisplay.jsp --><!-- BEGIN MerchandisingAssociationsDisplay.jsp --><!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp --><!-- END MerchandisingAssociationsDisplay.jsp -->\n" +
                "            <div class=\"product \">\n" +
                "            \t\n" +
                "                     \n" +
                "                <div class=\"productInfo\">\n" +
                "                    <div class=\"productNameAndPromotions\">\n" +
                "                        \n" +
                "                                  <h3>\n" +
                "                                    <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html\">\n" +
                "                                        Sainsbury's Strawberries 400g\n" +
                "                                        <img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/product_images/2017/May/media_7555699_L.jpg\" alt=\"\">\n" +
                "                                    </a>\n" +
                "                                </h3>\n" +
                "                              \n" +
                "                    </div> \n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"errorBanner hidden\" id=\"error124183\"></div>\n" +
                "                <div class=\"addToTrolleytabBox\">   \n" +
                "                    <!--addToTrolleytabBox GRID VIEW --><!-- Start UserSubscribedOrNot.jspf --><!-- Start UserSubscribedOrNot.jsp --><!-- \n" +
                "\t\t\tIf the user is not logged in, render this opening \n" +
                "\t\t\tDIV adding an addtional class to fix the border top which is removed \n" +
                "\t\t\tand replaced by the tabs\n" +
                "\t\t-->\n" +
                "\t\t<div class=\"addToTrolleytabContainer addItemBorderTop\">\n" +
                "\t<!-- End AddToSubscriptionList.jsp --><!-- End AddSubscriptionList.jspf --><!-- \n" +
                "                    ATTENTION!!!\n" +
                "                    <div class=\"addToTrolleytabContainer\">\n" +
                "                    This opening div is inside \"../../ReusableObjects/UserSubscribedOrNot.jsp\"\n" +
                "                    -->\n" +
                "                    <div class=\"pricingAndTrolleyOptions\">\n" +
                "                    \t\n" +
                "                                    <div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124183\">\n" +
                "                                       <div class=\"pricing\">\n" +
                "                                           \n" +
                "\t<p class=\"pricePerUnit\">\n" +
                "    £1.75<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">£4.38<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr>\n" +
                "    </p>\n" +
                "\n" +
                "                                       </div>                                      \n" +
                "\n" +
                "                                     \n" +
                "                                        <div class=\"addToTrolleyForm \"> \n" +
                "                                           <!-- BEGIN AddToTrolley.jsp --><!-- fire an on add to bag here from  -->\n" +
                "\t\t\n" +
                "\t\t<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_124183\" action=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_124183\">\n" +
                "\t\t    <input type=\"hidden\" name=\"storeId\" value=\"10151\">\n" +
                "\t\t    <input type=\"hidden\" name=\"langId\" value=\"44\">\n" +
                "\t\t    <input type=\"hidden\" name=\"catalogId\" value=\"10123\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t\t    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\">\n" +
                "\t\t    <input type=\"hidden\" name=\"SKU_ID\" value=\"7555699\">\n" +
                "\t\t    <input type=\"hidden\" name=\"itemType\" value=\"\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t           <input type=\"hidden\" name=\"ItemSKU_ID\" value=\"7555699\">\n" +
                "\t        \n" +
                "\t\t        <label class=\"access\" for=\"quantity_124182\">Quantity</label>\n" +
                "\t\t        \n" +
                "\t\t\t\t\t<input name=\"quantity\" id=\"quantity_124182\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\">\n" +
                "\t\t\t\t\n" +
                "                    <input type=\"hidden\" name=\"catEntryId\" value=\"124183\">\n" +
                "                \n" +
                "\n" +
                "\t\t        <input type=\"hidden\" name=\"productId\" value=\"124182\">\n" +
                "\t\t        \n" +
                "\t\t\n" +
                "\t\t    <input type=\"hidden\" name=\"URL\" value=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?msg=&amp;langId=44&amp;storeId=10151&amp;krypto=DiexKMV6VNfttm96bidCJW9VwVpyuXivgK5RapEr%2F1tOYmh4OCk10AVK5CaB3Yi6FFDZ3QKw8L0r3rATu6UcoQBY6ZDb%2ByG%2BZeRJrjKst%2B4gw4oCl%2Bcz3Bya2vManzZc44GH64eq9euGfffnFahovteIDFagnmf43nXVx6mqkUU%3D&amp;ddkey=http%3Agb%2Fgroceries%2Fberries-cherries-currants\">\n" +
                "\t\t    <input type=\"hidden\" name=\"page\" value=\"\">\n" +
                "\t\t    <input type=\"hidden\" name=\"contractId\" value=\"\">\n" +
                "\t\t    <input type=\"hidden\" name=\"calculateOrder\" value=\"1\">\n" +
                "\t\t    <input type=\"hidden\" name=\"calculationUsage\" value=\"-1,-2,-3\">\n" +
                "\t\t    <input type=\"hidden\" name=\"updateable\" value=\"1\">\n" +
                "\t\t    <input type=\"hidden\" name=\"merge\" value=\"***\">\n" +
                "\t\t     \n" +
                "\t\t   \t<input type=\"hidden\" name=\"callAjax\" value=\"false\">\n" +
                "\t\t   \t\n" +
                "\t\t    \n" +
                "\t\t        <input class=\"button process\" type=\"submit\" name=\"Add\" value=\"Add\">\n" +
                "\t\t\t\n" +
                "\n" +
                "\n" +
                "\t\t</form>\n" +
                "\t<!-- END AddToTrolley.jsp -->\n" +
                "        <div class=\"numberInTrolley hidden numberInTrolley_124182\" id=\"numberInTrolley_124182\">\n" +
                "            \n" +
                "        </div>\n" +
                "    \n" +
                "                                        </div>\n" +
                "\n" +
                "                                        <!-- BEGIN CatalogEntryRatingsReviewsInfo.jspf --><!-- productAllowedRatingsAndReviews: false --><!-- END CatalogEntryRatingsReviewsInfo.jspf -->\n" +
                "                                   </div>\n" +
                "                               <!-- Start AddToSubscriptionList.jspf --><!-- Start AddToSubscriptionList.jsp --><!-- End AddToSubscriptionList.jsp --><!-- End AddToSubscriptionList.jspf -->     \n" +
                "                    </div>\n" +
                "                            \n" +
                "                    \n" +
                "                </div>     \n" +
                "            </div>\n" +
                "            <!--ThumbnailRestriction --><!-- END CatalogEntryThumbnailDisplay.jsp -->\n" +
                "\t                            </div></li>";

        return Jsoup.parse(html);
    }

    private Document getDescriptionHtml() {
        return Jsoup.parse("<div class=\"section\" id=\"information\">\n" +
                "                <h2 class=\"access\">Information</h2>\n" +
                "                <productcontent xmlns:a=\"http://www.inspire-js.com/SOL\">\n" +
                "<htmlcontent contentpath=\"/Content/media/html/products/label//_label_inspire.html\" outputmethod=\"xhtml\">\n" +
                "<h3 class=\"productDataItemHeader\">Description</h3>\n" +
                "<div class=\"productText\">\n" +
                "<p>by Sainsbury's raspberries</p>\n" +
                "<p>\n" +
                "</p><p></p>\n" +
                "<p></p>\n" +
                "</div>\n" +
                "\n" +
                "<h3 class=\"productDataItemHeader\">Nutrition</h3>\n" +
                "<div class=\"productText\">\n" +
                "<div>\n" +
                "<p>\n" +
                "<strong>Table of Nutritional Information</strong>\n" +
                "</p>\n" +
                "<div class=\"tableWrapper\">\n" +
                "<table class=\"nutritionTable\">\n" +
                "<thead>\n" +
                "<tr class=\"tableTitleRow\">\n" +
                "<th scope=\"col\"></th><th scope=\"col\">Per 100g&nbsp;</th><th scope=\"col\">% based on RI for Average Adult</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody><tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\" rowspan=\"2\">Energy</th><td class=\"tableRow1\">133kJ</td><td class=\"tableRow1\">-</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow0\">\n" +
                "<td class=\"tableRow0\">32kcal</td><td class=\"tableRow0\">2%</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Fat</th><td class=\"tableRow1\">&lt;0.5g</td><td class=\"tableRow1\">-</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow0\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Saturates</th><td class=\"tableRow0\">&lt;0.1g</td><td class=\"tableRow0\">-</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Carbohydrate</th><td class=\"tableRow1\">4.6g</td><td class=\"tableRow1\">2%</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow0\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Total Sugars</th><td class=\"tableRow0\">4.6g</td><td class=\"tableRow0\">5%</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Fibre</th><td class=\"tableRow1\">2.5g</td><td class=\"tableRow1\">-</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow0\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Protein</th><td class=\"tableRow0\">1.4g</td><td class=\"tableRow0\">3%</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Salt</th><td class=\"tableRow1\">&lt;0.01g</td><td class=\"tableRow1\">-</td>\n" +
                "</tr>\n" +
                "</tbody></table>\n" +
                "</div>\n" +
                "<p>RI= Reference Intakes of an average adult (8400kJ / 2000kcal)</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<h3 class=\"productDataItemHeader\">Ingredients</h3>\n" +
                "<div class=\"productText\">\n" +
                "<p>This product conforms to the Sainsbury's Produce Sourcing Policy VP001&nbsp;\n" +
                "</p>\n" +
                "</div>\n" +
                "\n" +
                "<h3 class=\"productDataItemHeader\">Country of Origin</h3>\n" +
                "<div class=\"productText\"><p>Grown in Belgium, Guatemala,  Kenya, Mexico, Portugal,  South Africa, Spain, United Kingdom, USA, Netherlands, France, Morocco, Tanzania</p></div>\n" +
                "\n" +
                "<h3 class=\"productDataItemHeader\">Size</h3>\n" +
                "<div class=\"productText\">\n" +
                "<p>225gram</p>\n" +
                "</div>\n" +
                "\n" +
                "<h3 class=\"productDataItemHeader\">Packaging</h3>\n" +
                "<div class=\"productText\">\n" +
                "<p>Plastic - RPET punnet</p>\n" +
                "<p>Laminate film film</p>\n" +
                "</div>\n" +
                "\n" +
                "<h3 class=\"productDataItemHeader\">Manufacturer</h3>\n" +
                "<div class=\"productText\">\n" +
                "<p>We are happy to replace this item if it is not satisfactory</p>\n" +
                "<p>Sainsbury's Supermarkets Ltd.</p>\n" +
                "<p>33 Holborn, London EC1N 2HT</p>\n" +
                "<p>Customer services 0800 636262</p>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<!-- Mirrored from www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 08 Aug 2017 08:45:37 GMT -->\n" +
                "</htmlcontent>\n" +
                "</productcontent>\n" +
                "\n" +
                "                <p></p><h3>Important Information</h3><p>The above details have been prepared to help you select suitable products. Products and their ingredients are liable to change.</p><p><strong>You should always read the label before consuming or using the product and never rely solely on the information presented here.</strong></p><p>If you require specific advice on any Sainsbury's branded product, please contact our Customer Careline on 0800 636262. For all other products, please contact the manufacturer.</p><p>\n" +
                "This information is supplied for your personal use only. It may not be reproduced in any way without the prior consent of Sainsbury's Supermarkets Ltd and due acknowledgement.</p><p></p>\n" +
                "            </div>");
    }
}
