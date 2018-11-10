package com.test.scraper.parser;

import com.test.scraper.TestApplicationConfiguration;
import com.test.scraper.bean.ItemBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class PageParserTest {

    @Mock
    ItemParser itemParser;

    @InjectMocks
    private
    PageParser pageParser = new PageParserImpl();

    @Test
    public void shouldCallItemParserAndReturnItems() throws Exception {
        // Given a valid product page
        ItemBean stub = ItemBean.builder()
                .title("Stub")
                .unitPrice(1.0)
                .description("Stub")
                .kcalPer100g(1)
                .build();
        when(itemParser.extractCompleteItem(ArgumentMatchers.any())).thenReturn(stub);

        // When we call page parser
        List<ItemBean> items = pageParser.extractItems(givenProductPage());

        // Then we see parser call item parser and items are returned
        verify(itemParser, times(7)).extractCompleteItem(ArgumentMatchers.any());
        assertThat(items, hasSize(7));
    }

    private Document givenProductPage() {
        return Jsoup.parse("<ul class=\"productLister gridView\">\n" +
                "\t                \n" +
                "                                \n" +
                "\t                            <li class=\"gridItem\">\n" +
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
                "\t                            </div></li>\n" +
                "                                \n" +
                "                                \n" +
                "\t                            <li class=\"gridItem\">\n" +
                "\t                                <!-- BEGIN CatalogEntryThumbnailDisplay.jsp --><!-- BEGIN MerchandisingAssociationsDisplay.jsp --><!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp --><!-- END MerchandisingAssociationsDisplay.jsp -->\n" +
                "            <div class=\"product \">\n" +
                "            \t\n" +
                "                     \n" +
                "                <div class=\"productInfo\">\n" +
                "                    <div class=\"productNameAndPromotions\">\n" +
                "                        \n" +
                "                                  <h3>\n" +
                "                                    <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html\">\n" +
                "                                        Sainsbury's Blueberries 200g\n" +
                "                                        <img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/product_images/media_7555404_L.jpg\" alt=\"\">\n" +
                "                                    </a>\n" +
                "                                </h3>\n" +
                "                              \n" +
                "                    </div> \n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"errorBanner hidden\" id=\"error124079\"></div>\n" +
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
                "                                    <div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124079\">\n" +
                "                                       <div class=\"pricing\">\n" +
                "                                           \n" +
                "\t<p class=\"pricePerUnit\">\n" +
                "    £1.75<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">£8.75<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr>\n" +
                "    </p>\n" +
                "\n" +
                "                                       </div>                                      \n" +
                "\n" +
                "                                     \n" +
                "                                        <div class=\"addToTrolleyForm \"> \n" +
                "                                           <!-- BEGIN AddToTrolley.jsp --><!-- fire an on add to bag here from  -->\n" +
                "\t\t\n" +
                "\t\t<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_124079\" action=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_124079\">\n" +
                "\t\t    <input type=\"hidden\" name=\"storeId\" value=\"10151\">\n" +
                "\t\t    <input type=\"hidden\" name=\"langId\" value=\"44\">\n" +
                "\t\t    <input type=\"hidden\" name=\"catalogId\" value=\"10123\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t\t    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\">\n" +
                "\t\t    <input type=\"hidden\" name=\"SKU_ID\" value=\"7555404\">\n" +
                "\t\t    <input type=\"hidden\" name=\"itemType\" value=\"\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t           <input type=\"hidden\" name=\"ItemSKU_ID\" value=\"7555404\">\n" +
                "\t        \n" +
                "\t\t        <label class=\"access\" for=\"quantity_124078\">Quantity</label>\n" +
                "\t\t        \n" +
                "\t\t\t\t\t<input name=\"quantity\" id=\"quantity_124078\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\">\n" +
                "\t\t\t\t\n" +
                "                    <input type=\"hidden\" name=\"catEntryId\" value=\"124079\">\n" +
                "                \n" +
                "\n" +
                "\t\t        <input type=\"hidden\" name=\"productId\" value=\"124078\">\n" +
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
                "        <div class=\"numberInTrolley hidden numberInTrolley_124078\" id=\"numberInTrolley_124078\">\n" +
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
                "    \n" +
                "    <div class=\"badges\">\n" +
                "        <ul>\n" +
                "            \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                     <li class=\"lastchild\">\n" +
                "\n" +
                "                        \n" +
                "                            <a href=\"../../../../../../shop/gb/groceries/get-ideas/delivery-and-guides/freshness.html\">\n" +
                "                        \n" +
                "                        \n" +
                "                        <img src=\"../../../../../../wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/icons/typical_life/typical_life_2days.jpg\" alt=\"Typical life 2 days\">\n" +
                "                            \n" +
                "                        \n" +
                "                            </a>    \n" +
                "                        \n" +
                "                    </li>\n" +
                "                \n" +
                "        </ul>   \n" +
                "    </div>\n" +
                "\n" +
                "                </div>     \n" +
                "            </div>\n" +
                "            <!--ThumbnailRestriction --><!-- END CatalogEntryThumbnailDisplay.jsp -->\n" +
                "\t                            </div></li>\n" +
                "                                \n" +
                "                                \n" +
                "\t                            <li class=\"gridItem\">\n" +
                "\t                                <!-- BEGIN CatalogEntryThumbnailDisplay.jsp --><!-- BEGIN MerchandisingAssociationsDisplay.jsp --><!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp --><!-- END MerchandisingAssociationsDisplay.jsp -->\n" +
                "            <div class=\"product \">\n" +
                "            \t\n" +
                "                     \n" +
                "                <div class=\"productInfo\">\n" +
                "                    <div class=\"productNameAndPromotions\">\n" +
                "                        \n" +
                "                                  <h3>\n" +
                "                                    <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g.html\">\n" +
                "                                        Sainsbury's Raspberries 225g\n" +
                "                                        <img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/product_images/media_7555636_L.jpg\" alt=\"\">\n" +
                "                                    </a>\n" +
                "                                </h3>\n" +
                "                              \n" +
                "                    </div> \n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"errorBanner hidden\" id=\"error124147\"></div>\n" +
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
                "                                    <div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124147\">\n" +
                "                                       <div class=\"pricing\">\n" +
                "                                           \n" +
                "\t<p class=\"pricePerUnit\">\n" +
                "    £1.75<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">£7.78<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr>\n" +
                "    </p>\n" +
                "\n" +
                "                                       </div>                                      \n" +
                "\n" +
                "                                     \n" +
                "                                        <div class=\"addToTrolleyForm \"> \n" +
                "                                           <!-- BEGIN AddToTrolley.jsp --><!-- fire an on add to bag here from  -->\n" +
                "\t\t\n" +
                "\t\t<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_124147\" action=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_124147\">\n" +
                "\t\t    <input type=\"hidden\" name=\"storeId\" value=\"10151\">\n" +
                "\t\t    <input type=\"hidden\" name=\"langId\" value=\"44\">\n" +
                "\t\t    <input type=\"hidden\" name=\"catalogId\" value=\"10123\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t\t    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\">\n" +
                "\t\t    <input type=\"hidden\" name=\"SKU_ID\" value=\"7555636\">\n" +
                "\t\t    <input type=\"hidden\" name=\"itemType\" value=\"\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t           <input type=\"hidden\" name=\"ItemSKU_ID\" value=\"7555636\">\n" +
                "\t        \n" +
                "\t\t        <label class=\"access\" for=\"quantity_124146\">Quantity</label>\n" +
                "\t\t        \n" +
                "\t\t\t\t\t<input name=\"quantity\" id=\"quantity_124146\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\">\n" +
                "\t\t\t\t\n" +
                "                    <input type=\"hidden\" name=\"catEntryId\" value=\"124147\">\n" +
                "                \n" +
                "\n" +
                "\t\t        <input type=\"hidden\" name=\"productId\" value=\"124146\">\n" +
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
                "        <div class=\"numberInTrolley hidden numberInTrolley_124146\" id=\"numberInTrolley_124146\">\n" +
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
                "    \n" +
                "    <div class=\"badges\">\n" +
                "        <ul>\n" +
                "            \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                     <li class=\"lastchild\">\n" +
                "\n" +
                "                        \n" +
                "                            <a href=\"../../../../../../shop/gb/groceries/get-ideas/delivery-and-guides/freshness.html\">\n" +
                "                        \n" +
                "                        \n" +
                "                        <img src=\"../../../../../../wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/icons/typical_life/typical_life_2days.jpg\" alt=\"Typical life 2 days\">\n" +
                "                            \n" +
                "                        \n" +
                "                            </a>    \n" +
                "                        \n" +
                "                    </li>\n" +
                "                \n" +
                "        </ul>   \n" +
                "    </div>\n" +
                "\n" +
                "                </div>     \n" +
                "            </div>\n" +
                "            <!--ThumbnailRestriction --><!-- END CatalogEntryThumbnailDisplay.jsp -->\n" +
                "\t                            </div></li>\n" +
                "                                \n" +
                "                                \n" +
                "\t                            <li class=\"gridItem\">\n" +
                "\t                                <!-- BEGIN CatalogEntryThumbnailDisplay.jsp --><!-- BEGIN MerchandisingAssociationsDisplay.jsp --><!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp --><!-- END MerchandisingAssociationsDisplay.jsp -->\n" +
                "            <div class=\"product \">\n" +
                "            \t\n" +
                "                     \n" +
                "                <div class=\"productInfo\">\n" +
                "                    <div class=\"productNameAndPromotions\">\n" +
                "                        \n" +
                "                                  <h3>\n" +
                "                                    <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--sweet-150g.html\">\n" +
                "                                        Sainsbury's Blackberries, Sweet 150g\n" +
                "                                        <img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/ExtendedSitesCatalogAssetStore/images/catalog/productImages/32/0000001848632/0000001848632_L.jpg\" alt=\"\">\n" +
                "                                    </a>\n" +
                "                                </h3>\n" +
                "                              \n" +
                "                    </div> \n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"errorBanner hidden\" id=\"error126067\"></div>\n" +
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
                "                                    <div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_126067\">\n" +
                "                                       <div class=\"pricing\">\n" +
                "                                           \n" +
                "\t<p class=\"pricePerUnit\">\n" +
                "    £1.50<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">£10.00<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr>\n" +
                "    </p>\n" +
                "\n" +
                "                                       </div>                                      \n" +
                "\n" +
                "                                     \n" +
                "                                        <div class=\"addToTrolleyForm \"> \n" +
                "                                           <!-- BEGIN AddToTrolley.jsp --><!-- fire an on add to bag here from  -->\n" +
                "\t\t\n" +
                "\t\t<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_126067\" action=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_126067\">\n" +
                "\t\t    <input type=\"hidden\" name=\"storeId\" value=\"10151\">\n" +
                "\t\t    <input type=\"hidden\" name=\"langId\" value=\"44\">\n" +
                "\t\t    <input type=\"hidden\" name=\"catalogId\" value=\"10123\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t\t    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\">\n" +
                "\t\t    <input type=\"hidden\" name=\"SKU_ID\" value=\"7572753\">\n" +
                "\t\t    <input type=\"hidden\" name=\"itemType\" value=\"\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t           <input type=\"hidden\" name=\"ItemSKU_ID\" value=\"7572753\">\n" +
                "\t        \n" +
                "\t\t        <label class=\"access\" for=\"quantity_126066\">Quantity</label>\n" +
                "\t\t        \n" +
                "\t\t\t\t\t<input name=\"quantity\" id=\"quantity_126066\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\">\n" +
                "\t\t\t\t\n" +
                "                    <input type=\"hidden\" name=\"catEntryId\" value=\"126067\">\n" +
                "                \n" +
                "\n" +
                "\t\t        <input type=\"hidden\" name=\"productId\" value=\"126066\">\n" +
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
                "        <div class=\"numberInTrolley hidden numberInTrolley_126066\" id=\"numberInTrolley_126066\">\n" +
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
                "\t                            </div></li>\n" +
                "                                \n" +
                "                                \n" +
                "\t                            <li class=\"gridItem\">\n" +
                "\t                                <!-- BEGIN CatalogEntryThumbnailDisplay.jsp --><!-- BEGIN MerchandisingAssociationsDisplay.jsp --><!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp -->\n" +
                "    <div class=\"coverage ranged\"></div>\n" +
                "<!-- END MerchandisingAssociationsDisplay.jsp -->\n" +
                "\t            \t<div id=\"hasCrossSell_124101\" class=\"product hasCrossSell \"> \n" +
                "\t                \n" +
                "                        <div class=\"productInfo\">\n" +
                "                        \t<div class=\"productNameAndPromotions\">\n" +
                "                                \n" +
                "                                        <h3>\n" +
                "                                            <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html\">\n" +
                "                                                Sainsbury's Blueberries 400g\n" +
                "                                                <img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/ExtendedSitesCatalogAssetStore/images/catalog/productImages/74/0000001835274/0000001835274_L.jpg\" alt=\"\">\n" +
                "                                            </a>\n" +
                "                                        </h3>\n" +
                "                                        \n" +
                "                            </div>\n" +
                "                             \n" +
                "                        </div>\n" +
                "                        \n" +
                "                        \n" +
                "                        \n" +
                "                    \n" +
                "                    <div class=\"addToTrolleytabBox\">\n" +
                "                        <!-- Start UserSubscribedOrNot.jspf --><!-- Start UserSubscribedOrNot.jsp --><!-- \n" +
                "\t\t\tIf the user is not logged in, render this opening \n" +
                "\t\t\tDIV adding an addtional class to fix the border top which is removed \n" +
                "\t\t\tand replaced by the tabs\n" +
                "\t\t-->\n" +
                "\t\t<div class=\"addToTrolleytabContainer addItemBorderTop\">\n" +
                "\t<!-- End AddToSubscriptionList.jsp --><!-- End AddSubscriptionList.jspf --><!-- \n" +
                "                        ATTENTION!!!\n" +
                "                        <div class=\"addToTrolleytabContainer\">\n" +
                "                        This opening div is inside \"../../ReusableObjects/UserSubscribedOrNot.jsp\"\n" +
                "                        -->\n" +
                "                        \n" +
                "\t\t                <!--  <div class=\"pricingAndTrolleyFormWrapper\"> -->\n" +
                "\t                <div class=\"pricingAndTrolleyOptions\">   \n" +
                "\t                    <div class=\"priceTab activeContainer priceTabContainer\" id=\"addItem_124101\"> \n" +
                "\t                        <div class=\"pricing\">\n" +
                "\t                            \n" +
                "\t<p class=\"pricePerUnit\">\n" +
                "    £3.25<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">£8.13<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr>\n" +
                "    </p>\n" +
                "\n" +
                "\t                        </div>                        \n" +
                "\t                        \n" +
                "\t                                    <div class=\"addToTrolleyForm \">                                    \t\n" +
                "\t                                        \n" +
                "\t                                        <!-- BEGIN AddToTrolley.jsp --><!-- fire an on add to bag here from  -->\n" +
                "\t\t\n" +
                "\t\t<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_124101\" action=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_124101\">\n" +
                "\t\t    <input type=\"hidden\" name=\"storeId\" value=\"10151\">\n" +
                "\t\t    <input type=\"hidden\" name=\"langId\" value=\"44\">\n" +
                "\t\t    <input type=\"hidden\" name=\"catalogId\" value=\"10123\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t\t    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\">\n" +
                "\t\t    <input type=\"hidden\" name=\"SKU_ID\" value=\"7555490\">\n" +
                "\t\t    <input type=\"hidden\" name=\"itemType\" value=\"\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t           <input type=\"hidden\" name=\"ItemSKU_ID\" value=\"7555490\">\n" +
                "\t        \n" +
                "\t\t        <label class=\"access\" for=\"quantity_124100\">Quantity</label>\n" +
                "\t\t        \n" +
                "\t\t\t\t\t<input name=\"quantity\" id=\"quantity_124100\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\">\n" +
                "\t\t\t\t\n" +
                "                    <input type=\"hidden\" name=\"catEntryId\" value=\"124101\">\n" +
                "                \n" +
                "\n" +
                "\t\t        <input type=\"hidden\" name=\"productId\" value=\"124100\">\n" +
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
                "        <div class=\"numberInTrolley hidden numberInTrolley_124100\" id=\"numberInTrolley_124100\">\n" +
                "            \n" +
                "        </div>\n" +
                "    \n" +
                "\t                                    </div>\n" +
                "\t\t                                \n" +
                "                        </div>\n" +
                "                        <!-- Start AddToSubscriptionList.jspf --><!-- Start AddToSubscriptionList.jsp --><!-- End AddToSubscriptionList.jsp --><!-- End AddToSubscriptionList.jspf -->\n" +
                "                    </div>\n" +
                "\t\t                        \n" +
                "                        <!-- </div> -->\n" +
                "                </div>     \n" +
                "        \t</div>\n" +
                "\n" +
                "            <!--ThumbnailRestriction -->\n" +
                "                        \n" +
                "            <div class=\"errorBanner hidden\" id=\"error124101\"></div>                                 \n" +
                "               <div id=\"additionalItems_124101\" class=\"additionalItems\">\n" +
                "\t\t\t        <!-- BEGIN MerchandisingAssociationsDisplay.jsp --><!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp --><!-- BEGIN CatalogEntryThumbnailMerchandisingAssociation.jspf -->                    \n" +
                "                    <div id=\"sitecatalyst_SELL_TYPE_708978\" class=\"siteCatalystTag\">X-SELL</div>\n" +
                "                    <div class=\"crossSell\">\n" +
                "                        <div class=\"crossSellTitle\">\n" +
                "                            <!-- BEGIN ContentDisplay.jsp -->\n" +
                "\t                <img src=\"../../../../../../wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/merchandising_associations/lunchbox_favourites_grid_202x32.gif\" alt=\"wcassets/merchandising_associations/lunchbox_favourites_grid_202x32.gif\" border=\"0\">\n" +
                "\t                <!-- end: ContentDisplay.jsp -->\n" +
                "                        </div>\n" +
                "                        <div class=\"errorBanner hidden\" id=\"error124100708979\"></div>\n" +
                "                        <div class=\"crossSellContent\">\n" +
                "\t                        <div class=\"pricingAndTrolleyFormWrapper\"> \n" +
                "\t\t                         <div class=\"pricingReviews\">\n" +
                "\t\t                            <div class=\"pricing\">\n" +
                "\t\t                                \n" +
                "\t<p class=\"pricePerUnit\">\n" +
                "    £2.50<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">£2.50<abbr title=\"per\">/</abbr><abbr title=\"each\"><span class=\"pricePerMeasureMeasure\">ea</span></abbr>\n" +
                "    </p>\n" +
                "\n" +
                "\t\t                            </div>\n" +
                "\t\t                        </div>\n" +
                "\t\t                        <div class=\"addToTrolleyForm \">\n" +
                "\t\t                           \n" +
                "\t\t                            <!-- BEGIN AddToTrolley.jsp --><!-- fire an on add to bag here from  -->\n" +
                "\t\t\n" +
                "\t\t<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_708979_124100\" action=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_124100_708979\">\n" +
                "\t\t    <input type=\"hidden\" name=\"storeId\" value=\"10151\">\n" +
                "\t\t    <input type=\"hidden\" name=\"langId\" value=\"44\">\n" +
                "\t\t    <input type=\"hidden\" name=\"catalogId\" value=\"10123\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t\t    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\">\n" +
                "\t\t    <input type=\"hidden\" name=\"SKU_ID\" value=\"125775635\">\n" +
                "\t\t    <input type=\"hidden\" name=\"itemType\" value=\"X-SELL\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t           <input type=\"hidden\" name=\"ItemSKU_ID\" value=\"125775635\">\n" +
                "\t        \n" +
                "\t\t        <label class=\"access\" for=\"quantity_708978\">Quantity</label>\n" +
                "\t\t        \n" +
                "\t\t\t\t\t<input name=\"quantity\" id=\"quantity_708978\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\">\n" +
                "\t\t\t\t\n" +
                "                    <input type=\"hidden\" name=\"catEntryId\" value=\"708979\">\n" +
                "                \n" +
                "\n" +
                "\t\t        <input type=\"hidden\" name=\"productId\" value=\"708978\">\n" +
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
                "        <div class=\"numberInTrolley hidden numberInTrolley_708978\" id=\"numberInTrolley_708978\">\n" +
                "            \n" +
                "        </div>\n" +
                "    \n" +
                "\t\t                        </div>\n" +
                "\t\t                    </div>                \n" +
                "\t                        <div class=\"crossSellInfo\">\n" +
                "\t                            <h4 class=\"crossSellName\">\n" +
                "                                    <span class=\"access\">Try this product with </span>\n" +
                "\t                                <a href=\"../../../../../../shop/gb/groceries/sainsburys-140ml-klip-lock-storage-set---3pk.html\">\n" +
                "\t                                    Sainsbury's Klip Lock Storage Set 140ml x3\n" +
                "\t                                    <img src=\"../../../../../../wcsstore7.23.1.52/ExtendedSitesCatalogAssetStore/images/catalog/productImages/80/5054014273580/5054014273580_S.jpg\" alt=\"\">\n" +
                "\t                                </a>\n" +
                "\t                            </h4>\n" +
                "\t                            \n" +
                "\t                        </div>\n" +
                "\t                    </div>\n" +
                "\t                    <!--ThumbnailRestriction -->\n" +
                "                    </div>                \n" +
                "            \n" +
                "                <!-- END CatalogEntryThumbnailMerchandisingAssociation.jspf --><!-- END MerchandisingAssociationsDisplay.jsp --> \n" +
                "\t    \t\t</div>\n" +
                "\t    <!-- END CatalogEntryThumbnailDisplay.jsp -->\n" +
                "\t                            </div></li>\n" +
                "                                \n" +
                "                                \n" +
                "\t                            <li class=\"gridItem\">\n" +
                "\t                                <!-- BEGIN CatalogEntryThumbnailDisplay.jsp --><!-- BEGIN MerchandisingAssociationsDisplay.jsp --><!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp --><!-- END MerchandisingAssociationsDisplay.jsp -->\n" +
                "            <div class=\"product \">\n" +
                "            \t\n" +
                "                     \n" +
                "                <div class=\"productInfo\">\n" +
                "                    <div class=\"productNameAndPromotions\">\n" +
                "                        \n" +
                "                                  <h3>\n" +
                "                                    <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries--so-organic-150g.html\">\n" +
                "                                        Sainsbury's Blueberries, SO Organic 150g\n" +
                "                                        <img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/product_images/media_7555452_L.jpg\" alt=\"\">\n" +
                "                                    </a>\n" +
                "                                </h3>\n" +
                "                              \n" +
                "                    </div> \n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"errorBanner hidden\" id=\"error124087\"></div>\n" +
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
                "                                    <div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124087\">\n" +
                "                                       <div class=\"pricing\">\n" +
                "                                           \n" +
                "\t<p class=\"pricePerUnit\">\n" +
                "    £2.00<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">£13.33<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr>\n" +
                "    </p>\n" +
                "\n" +
                "                                       </div>                                      \n" +
                "\n" +
                "                                     \n" +
                "                                        <div class=\"addToTrolleyForm \"> \n" +
                "                                           <!-- BEGIN AddToTrolley.jsp --><!-- fire an on add to bag here from  -->\n" +
                "\t\t\n" +
                "\t\t<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_124087\" action=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_124087\">\n" +
                "\t\t    <input type=\"hidden\" name=\"storeId\" value=\"10151\">\n" +
                "\t\t    <input type=\"hidden\" name=\"langId\" value=\"44\">\n" +
                "\t\t    <input type=\"hidden\" name=\"catalogId\" value=\"10123\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t\t    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\">\n" +
                "\t\t    <input type=\"hidden\" name=\"SKU_ID\" value=\"7555452\">\n" +
                "\t\t    <input type=\"hidden\" name=\"itemType\" value=\"\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t           <input type=\"hidden\" name=\"ItemSKU_ID\" value=\"7555452\">\n" +
                "\t        \n" +
                "\t\t        <label class=\"access\" for=\"quantity_124086\">Quantity</label>\n" +
                "\t\t        \n" +
                "\t\t\t\t\t<input name=\"quantity\" id=\"quantity_124086\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\">\n" +
                "\t\t\t\t\n" +
                "                    <input type=\"hidden\" name=\"catEntryId\" value=\"124087\">\n" +
                "                \n" +
                "\n" +
                "\t\t        <input type=\"hidden\" name=\"productId\" value=\"124086\">\n" +
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
                "        <div class=\"numberInTrolley hidden numberInTrolley_124086\" id=\"numberInTrolley_124086\">\n" +
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
                "    \n" +
                "    <div class=\"badges\">\n" +
                "        <ul>\n" +
                "            \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                     <li class=\"lastchild\">\n" +
                "\n" +
                "                        \n" +
                "                            <a href=\"../../../../../../shop/gb/groceries/get-ideas/delivery-and-guides/freshness.html\">\n" +
                "                        \n" +
                "                        \n" +
                "                        <img src=\"../../../../../../wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/icons/typical_life/typical_life_3days.jpg\" alt=\"Typical life 3 days\">\n" +
                "                            \n" +
                "                        \n" +
                "                            </a>    \n" +
                "                        \n" +
                "                    </li>\n" +
                "                \n" +
                "        </ul>   \n" +
                "    </div>\n" +
                "\n" +
                "                </div>     \n" +
                "            </div>\n" +
                "            <!--ThumbnailRestriction --><!-- END CatalogEntryThumbnailDisplay.jsp -->\n" +
                "\t                            </div></li>\n" +
                "                                \n" +
                "                                \n" +
                "\t                            <li class=\"gridItem\">\n" +
                "\t                                <!-- BEGIN CatalogEntryThumbnailDisplay.jsp --><!-- BEGIN MerchandisingAssociationsDisplay.jsp --><!-- Start - JSP File Name:  MerchandisingAssociationsDisplay.jsp --><!-- END MerchandisingAssociationsDisplay.jsp -->\n" +
                "            <div class=\"product \">\n" +
                "            \t\n" +
                "                     \n" +
                "                <div class=\"productInfo\">\n" +
                "                    <div class=\"productNameAndPromotions\">\n" +
                "                        \n" +
                "                                  <h3>\n" +
                "                                    <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries--taste-the-difference-150g.html\">\n" +
                "                                        Sainsbury's Raspberries, Taste the Difference 150g\n" +
                "                                        <img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/ExtendedSitesCatalogAssetStore/images/catalog/productImages/42/0000001835342/0000001835342_L.jpg\" alt=\"\">\n" +
                "                                    </a>\n" +
                "                                </h3>\n" +
                "                              \n" +
                "                    </div> \n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"errorBanner hidden\" id=\"error124163\"></div>\n" +
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
                "                                    <div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124163\">\n" +
                "                                       <div class=\"pricing\">\n" +
                "                                           \n" +
                "\t<p class=\"pricePerUnit\">\n" +
                "    £2.50<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">£16.67<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr>\n" +
                "    </p>\n" +
                "\n" +
                "                                       </div>                                      \n" +
                "\n" +
                "                                     \n" +
                "                                        <div class=\"addToTrolleyForm \"> \n" +
                "                                           <!-- BEGIN AddToTrolley.jsp --><!-- fire an on add to bag here from  -->\n" +
                "\t\t\n" +
                "\t\t<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_124163\" action=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_124163\">\n" +
                "\t\t    <input type=\"hidden\" name=\"storeId\" value=\"10151\">\n" +
                "\t\t    <input type=\"hidden\" name=\"langId\" value=\"44\">\n" +
                "\t\t    <input type=\"hidden\" name=\"catalogId\" value=\"10123\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t\t    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\">\n" +
                "\t\t    <input type=\"hidden\" name=\"SKU_ID\" value=\"7555668\">\n" +
                "\t\t    <input type=\"hidden\" name=\"itemType\" value=\"\">\n" +
                "\t\t    \n" +
                "\t\t    \n" +
                "\t           <input type=\"hidden\" name=\"ItemSKU_ID\" value=\"7555668\">\n" +
                "\t        \n" +
                "\t\t        <label class=\"access\" for=\"quantity_124162\">Quantity</label>\n" +
                "\t\t        \n" +
                "\t\t\t\t\t<input name=\"quantity\" id=\"quantity_124162\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\">\n" +
                "\t\t\t\t\n" +
                "                    <input type=\"hidden\" name=\"catEntryId\" value=\"124163\">\n" +
                "                \n" +
                "\n" +
                "\t\t        <input type=\"hidden\" name=\"productId\" value=\"124162\">\n" +
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
                "        <div class=\"numberInTrolley hidden numberInTrolley_124162\" id=\"numberInTrolley_124162\">\n" +
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
                "    \n" +
                "    <div class=\"badges\">\n" +
                "        <ul>\n" +
                "            \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                     <li class=\"lastchild\">\n" +
                "\n" +
                "                        \n" +
                "                            <a href=\"../../../../../../shop/gb/groceries/get-ideas/delivery-and-guides/freshness.html\">\n" +
                "                        \n" +
                "                        \n" +
                "                        <img src=\"../../../../../../wcsstore7.23.1.52/SainsburysStorefrontAssetStore/wcassets/icons/typical_life/typical_life_2days.jpg\" alt=\"Typical life 2 days\">\n" +
                "                            \n" +
                "                        \n" +
                "                            </a>    \n" +
                "                        \n" +
                "                    </li>\n" +
                "                \n" +
                "        </ul>   \n" +
                "    </div>\n" +
                "\n" +
                "                </div>     \n" +
                "            </div>\n" +
                "            <!--ThumbnailRestriction --><!-- END CatalogEntryThumbnailDisplay.jsp -->\n" +
                "\t                            </div></li>\n" +
                "\t            </ul>");
    }
}
