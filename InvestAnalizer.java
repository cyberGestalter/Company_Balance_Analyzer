import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import model.Model;
import model.models.DataForCalculation;
import model.repository.AnalysisCheckS;
import model.repository.Companies;
import model.service.SharesDBManipulator;
import model.service.parser.investing.CompanyParser;
import model.service.parser.investing.DataForCalculationParser;
import model.service.parser.investing.SearchStringParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Главный класс программы
public class InvestAnalizer {
    public static void main(String[] args) {
        //view
        //Открыть графический интерфейс приложения

        //Найти/выбрать/написать название компании или тикер ее акций
        //Выбрать временной интервал для сбора данных
        //model.service.parser
        //Собрать данные в БД

        //Посчитать показатели на основе собранных данных
        //Вывести заключение об адекватности вложений в акции выбранной компании
        Model model = null;
        int n = -1;
        List<String> searchList = new ArrayList<>();
        searchList.add("https://www.investing.com/equities/sberbank_rts");
        searchList.add("https://www.investing.com/equities/abrau-durso-oao");
        searchList.add("https://www.investing.com/equities/aeroflot");
        searchList.add("https://www.investing.com/equities/afk-sistema_rts");
        searchList.add("https://www.investing.com/equities/akb-avangard-oao");
        searchList.add("https://www.investing.com/equities/rosbank");
        searchList.add("https://www.investing.com/equities/alrosa-ao");
        searchList.add("https://www.investing.com/equities/apteka-36-6_rts");
        searchList.add("https://www.investing.com/equities/armada");
        searchList.add("https://www.investing.com/equities/astrakhan-power-sale-comp");
        searchList.add("https://www.investing.com/equities/bank-kuznetskiy-oao");
        searchList.add("https://www.investing.com/equities/bank-uralsib");
        searchList.add("https://www.investing.com/equities/bashinformsvyaz");
        searchList.add("https://www.investing.com/equities/bashneft_rts");
        searchList.add("https://www.investing.com/equities/belon_rts");
        searchList.add("https://www.investing.com/equities/synergy_rts");
        searchList.add("https://www.investing.com/equities/alor-bank-oao");
        searchList.add("https://www.investing.com/equities/bank-st-petersbr_rts");
        searchList.add("https://www.investing.com/equities/buryatzoloto");
        searchList.add("https://www.investing.com/equities/chelyabenergosbyt");
        searchList.add("https://www.investing.com/equities/chmk");
        searchList.add("https://www.investing.com/equities/chelyabinskiy-kuznechno-presso");
        searchList.add("https://www.investing.com/equities/chernorgorneft");
        searchList.add("https://www.investing.com/equities/chtpz");
        searchList.add("https://www.investing.com/equities/chzpsn-profnastil-oao");
        searchList.add("https://www.investing.com/equities/cmt");
        //searchList.add("https://www.investing.com/equities/dagestan-sb"); // странице ничего нет, программа завершается
        searchList.add("https://www.investing.com/equities/dec");
        searchList.add("https://www.investing.com/equities/detskiy-mir-pao");
        searchList.add("https://www.investing.com/equities/diod-oao");
        searchList.add("https://www.investing.com/equities/donskoy-zavod-radiodetaley-oao");
        searchList.add("https://www.investing.com/equities/dvmp-oao");
        searchList.add("https://www.investing.com/equities/electrozinc");
        searchList.add("https://www.investing.com/equities/en-drc");
        searchList.add("https://www.investing.com/equities/ogk-5");
        searchList.add("https://www.investing.com/equities/pharmsynthez");
        searchList.add("https://www.investing.com/equities/fg-budushcheye-pao");
        searchList.add("https://www.investing.com/equities/fsk-ees_rts");
        searchList.add("https://www.investing.com/equities/gals-development");
        searchList.add("https://www.investing.com/equities/gaz-auto-plant");
        searchList.add("https://www.investing.com/equities/gazprom-gazoraspredeleniye");
        searchList.add("https://www.investing.com/equities/gazprom-neft_rts");
        searchList.add("https://www.investing.com/equities/gazprom_rts");
        searchList.add("https://www.investing.com/equities/gk-rollman-oao");
        searchList.add("https://www.investing.com/equities/gk-tns-energo-pao");
        searchList.add("https://www.investing.com/equities/globaltrak");
        searchList.add("https://www.investing.com/equities/gorodskiye-innovatsionnyye-tekhnolo");
        searchList.add("https://www.investing.com/equities/gruppa-cherkizovo");
        searchList.add("https://www.investing.com/equities/lsr-group_rts");
        searchList.add("https://www.investing.com/equities/gtl-oao");
        searchList.add("https://www.investing.com/equities/khimprom");
        searchList.add("https://www.investing.com/equities/human-stem-cells-institute");
        searchList.add("https://www.investing.com/equities/inter-rao-ees_mm");
        searchList.add("https://www.investing.com/equities/invest-development-pao");
        searchList.add("https://www.investing.com/equities/irkutskenergo");
        searchList.add("https://www.investing.com/equities/izhstal");
        searchList.add("https://www.investing.com/equities/kaluga-power-sale-comp");
        searchList.add("https://www.investing.com/equities/kamaz");
        searchList.add("https://www.investing.com/equities/kamchatskenergo");
        searchList.add("https://www.investing.com/equities/kazanorgsintez");
        searchList.add("https://www.investing.com/equities/kazanskoye-motoros-proizvod");
        searchList.add("https://www.investing.com/equities/kurganskaya-generiruyushchaya-komp");
        searchList.add("https://www.investing.com/equities/kombinat-yuzhuralnikel-oao");
        searchList.add("https://www.investing.com/equities/korshynov-mining-plant");
        searchList.add("https://www.investing.com/equities/kostroma-retail-company");
        searchList.add("https://www.investing.com/equities/kovrovskiy-mekhanicheskiy");
        searchList.add("https://www.investing.com/equities/krasnoyarskenergosbyt");
        searchList.add("https://www.investing.com/equities/krasnyj-octyabr-co.");
        searchList.add("https://www.investing.com/equities/kubanenergosbyt-oao");
        searchList.add("https://www.investing.com/equities/kubanenergo-oao");
        searchList.add("https://www.investing.com/equities/kuibyshevazot");
        searchList.add("https://www.investing.com/equities/kuzbasskaya-toplivnaya");
        searchList.add("https://www.investing.com/equities/quadra---power-generation");
        searchList.add("https://www.investing.com/equities/lenenergo");
        searchList.add("https://www.investing.com/equities/lenta-ltd?cid=962408");
        searchList.add("https://www.investing.com/equities/lenzoloto-oao");
        searchList.add("https://www.investing.com/equities/levenguk-oao");
        searchList.add("https://www.investing.com/equities/lipetsk-power-sale-comp");
        searchList.add("https://www.investing.com/equities/lukoil_rts");
        searchList.add("https://www.investing.com/equities/magadanenergo");
        searchList.add("https://www.investing.com/equities/magnit_rts");
        searchList.add("https://www.investing.com/equities/marienergosbyt");
        searchList.add("https://www.investing.com/equities/media-gruppa-voyna-i-mir-oao");
        searchList.add("https://www.investing.com/equities/o2-tv");
        searchList.add("https://www.investing.com/equities/megafon-oao");
        searchList.add("https://www.investing.com/equities/gmk-noril-nickel_rts");
        searchList.add("https://www.investing.com/equities/mmk_rts");
        searchList.add("https://www.investing.com/equities/mmtsb-pao"); //пустой год
        searchList.add("https://www.investing.com/equities/mordovskaya-energosbytovaya");
        searchList.add("https://www.investing.com/equities/morion-oao");
        searchList.add("https://www.investing.com/equities/mos-obl-sb_rts");
        searchList.add("https://www.investing.com/equities/mosenergo_rts");
        searchList.add("https://www.investing.com/equities/moskovskaya-birzha-oao");
        searchList.add("https://www.investing.com/equities/moscow-city-telephone-network");
        searchList.add("https://www.investing.com/equities/moskovskiy-kreditnyi-bank-oao");
        searchList.add("https://www.investing.com/equities/mosoblbank");
        searchList.add("https://www.investing.com/equities/mostotrest_rts");
        searchList.add("https://www.investing.com/equities/mrsk-centra");
        searchList.add("https://www.investing.com/equities/mrsk-cip");
        searchList.add("https://www.investing.com/equities/mrsk-severnogo-kavkaza");
        searchList.add("https://www.investing.com/equities/mrsk-severo-zapada");
        searchList.add("https://www.investing.com/equities/mrsk-sibiri");
        searchList.add("https://www.investing.com/equities/mrsk-urala-ao");
        searchList.add("https://www.investing.com/equities/mrsk-volgi");
        searchList.add("https://www.investing.com/equities/mrsk-yuga");
        searchList.add("https://www.investing.com/equities/mts_rts");
        searchList.add("https://www.investing.com/equities/multisistema-oao");
        searchList.add("https://www.investing.com/equities/mvideo_rts");
        searchList.add("https://www.investing.com/equities/mvz-im-milya");
        searchList.add("https://www.investing.com/equities/nauka-svyaz");
        searchList.add("https://www.investing.com/equities/nefaz");
        searchList.add("https://www.investing.com/equities/nizhegorodskaya-sbytovaya-komp");
        searchList.add("https://www.investing.com/equities/nizhnekamskshina");
        searchList.add("https://www.investing.com/equities/nizhnekamskneftekhim");
        searchList.add("https://www.investing.com/equities/rosneft_rts");
        searchList.add("https://www.investing.com/equities/ruspetro");
        searchList.add("https://www.investing.com/equities/nkhp-pao");
        searchList.add("https://www.investing.com/equities/nlmk_rts");
        searchList.add("https://www.investing.com/equities/nmtp_rts");
        searchList.add("https://www.investing.com/equities/novatek_rts");
        searchList.add("https://www.investing.com/equities/irkut-corp");
        searchList.add("https://www.investing.com/equities/npk-ovk-pao");
        searchList.add("https://www.investing.com/equities/nauka");

        searchList.add("https://www.investing.com/equities/obyedinennye-kreditnye-sist");
        searchList.add("https://www.investing.com/equities/ogk-2_rts");
        searchList.add("https://www.investing.com/equities/or-pao");
        searchList.add("https://www.investing.com/equities/otkrytye-investitsii-oao");
        searchList.add("https://www.investing.com/equities/pavlovskiy-avtobus-oao");
        searchList.add("https://www.investing.com/equities/perm-sb");
        searchList.add("https://www.investing.com/equities/phosagro");
        searchList.add("https://www.investing.com/equities/pifn-meridian-oao");
        searchList.add("https://www.investing.com/equities/pik_rts");
        searchList.add("https://www.investing.com/equities/plazmek-oao");
        searchList.add("https://www.investing.com/equities/polymetal?cid=44465");
        searchList.add("https://www.investing.com/equities/polyus-zoloto_rts");
        searchList.add("https://www.investing.com/equities/protek_rts");
        searchList.add("https://www.investing.com/equities/qiwi-plc?cid=960754");
        searchList.add("https://www.investing.com/equities/raspadskaya");
        searchList.add("https://www.investing.com/equities/rbk-tv-moskva");
        searchList.add("https://www.investing.com/equities/rsc-energia");
        searchList.add("https://www.investing.com/equities/ros-agro-plc");
        searchList.add("https://www.investing.com/equities/rosdorbank-pao");
        searchList.add("https://www.investing.com/equities/rosgosstrakh-oao");
        searchList.add("https://www.investing.com/equities/rosinter-restaurants-holding");
        searchList.add("https://www.investing.com/equities/rosseti-ao");
        searchList.add("https://www.investing.com/equities/rostelecom");
        searchList.add("https://www.investing.com/equities/rostov-sb");
        searchList.add("https://www.investing.com/equities/rusgrain-holding-oao");
        searchList.add("https://www.investing.com/equities/gidroogk-011d");
        searchList.add("https://www.investing.com/equities/rusolovo-oao");
        searchList.add("https://www.investing.com/equities/ruspolimet");
        searchList.add("https://www.investing.com/equities/ic-russ-invest");
        searchList.add("https://www.investing.com/equities/russian-sea-group");
        searchList.add("https://www.investing.com/equities/ryazan-sb");
        searchList.add("https://www.investing.com/equities/yevroplan-pao");
        searchList.add("https://www.investing.com/equities/sakhalinenergo-oao");
        searchList.add("https://www.investing.com/equities/samaraenergo");
        searchList.add("https://www.investing.com/equities/saratovenergo");
        searchList.add("https://www.investing.com/equities/saratov-oil-refenery");
        searchList.add("https://www.investing.com/equities/seligdar");
        searchList.add("https://www.investing.com/equities/north-western-shipping-compv");
        searchList.add("https://www.investing.com/equities/severstal_rts");
        searchList.add("https://www.investing.com/equities/sg-mechel_rts");
        searchList.add("https://www.investing.com/equities/sibirskiy-gostinets-pao");
        searchList.add("https://www.investing.com/equities/sinarskiy-trubnyi");
        searchList.add("https://www.investing.com/equities/slavneft");
        searchList.add("https://www.investing.com/equities/solikamskiy-magniyevyi-zavod");
        searchList.add("https://www.investing.com/equities/sollers");
        searchList.add("https://www.investing.com/equities/stavropolenergosbyt");
        searchList.add("https://www.investing.com/equities/surgutneftegas_rts");
        searchList.add("https://www.investing.com/equities/tambov-power-sale");
        searchList.add("https://www.investing.com/equities/tantal");
        searchList.add("https://www.investing.com/equities/tatneft_rts");
        searchList.add("https://www.investing.com/equities/tattelecom");
        searchList.add("https://www.investing.com/equities/tcs-group-holding-plc?cid=1153662");
        searchList.add("https://www.investing.com/equities/tgk-1");
        searchList.add("https://www.investing.com/equities/tgc-14");
        searchList.add("https://www.investing.com/equities/tgk-2");
        searchList.add("https://www.investing.com/equities/taganrogskiy-kombaynovyi-zavod-oao");
        searchList.add("https://www.investing.com/equities/krasny-kotelshchik");
        searchList.add("https://www.investing.com/equities/tomsk-distribution");
        searchList.add("https://www.investing.com/equities/transcontainer");
        searchList.add("https://www.investing.com/equities/tmk");
        searchList.add("https://www.investing.com/equities/central-telegraph");
        searchList.add("https://www.investing.com/equities/tuchkovskiy-kombinat-stroiteln");
        searchList.add("https://www.investing.com/equities/tza-oao");
        searchList.add("https://www.investing.com/equities/united-aircraft-corporation");
        searchList.add("https://www.investing.com/equities/arsagera");
        searchList.add("https://www.investing.com/equities/e.on-russia");
        searchList.add("https://www.investing.com/equities/united-company-rusal-plc%60");
        searchList.add("https://www.investing.com/equities/uralkaliy_rts");
        searchList.add("https://www.investing.com/equities/uralskaya-kuznitsa-oao");
        searchList.add("https://www.investing.com/equities/utair-aviakompaniya-oao");
        searchList.add("https://www.investing.com/equities/south-kuzbass");
        searchList.add("https://www.investing.com/equities/varyeganneftegaz");
        searchList.add("https://www.investing.com/equities/vsmpo-avisma-crp_rts");
        searchList.add("https://www.investing.com/equities/vladimirenergosbyt-oao");
        searchList.add("https://www.investing.com/equities/vladimirskiy-khimicheskiy");
        searchList.add("https://www.investing.com/equities/volgogradenergosbyt");
        searchList.add("https://www.investing.com/equities/voronezh-sb");
        searchList.add("https://www.investing.com/equities/vozrozhdenie_rts");
        searchList.add("https://www.investing.com/equities/vtb_rts");
        searchList.add("https://www.investing.com/equities/vyborgskiy-sudostroitelnyi-pao");
        searchList.add("https://www.investing.com/equities/vyborgskiy-sudostroitelnyi-zavod");
        searchList.add("https://www.investing.com/equities/x5-retail-grp?cid=1061926");
        searchList.add("https://www.investing.com/equities/yakutskenergo");
        searchList.add("https://www.investing.com/equities/yandex?cid=102063");
        searchList.add("https://www.investing.com/equities/yask");
        searchList.add("https://www.investing.com/equities/yatek");
        searchList.add("https://www.investing.com/equities/yevropeyskaya-elektrotekhnika");
        searchList.add("https://www.investing.com/equities/yuzhural-asko");
        searchList.add("https://www.investing.com/equities/amo-zil");
        searchList.add("https://www.investing.com/equities/zvezda");

        //searchList.add("https://www.investing.com/equities/novo-nordis");
        //searchList.add("https://www.investing.com/equities/astrazeneca-plc-ads");
        /*searchList.add("https://www.investing.com/equities/sanofi");
        searchList.add("https://www.investing.com/equities/novartis-ag");
        searchList.add("https://www.investing.com/equities/procter-gamble");*/
        //searchList.add("");
        //BufferedWriter writer = null;
        try {
            //writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/master21/Рабочий стол/Borya_12_08_2019" +
            //      "/Новый том/комп устройство/Intellij Idea/My_projects/MyProjects/InvestmentsAnalyzer/src/mistakes.txt", true)));

            model = new Model(true);
            for (String searchString : searchList) {
                n++;
                try {
                    model.analyse(searchString);

                } catch (Exception e) {
                    try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/home/master21/Рабочий стол/Borya_12_08_2019" +
                            "/Новый том/комп устройство/Intellij Idea/My_projects/MyProjects/InvestmentsAnalyzer/src/mistakes.txt", true)))) {
                        writer.write("Ссылка "+searchString+". На итерации [" + n + "] возникло исключение: ");
                        e.printStackTrace(writer);
                        writer.write("\n");
                        writer.write("\n");
                    }
                }
            }
            //writer.close();

        } catch (SQLException | FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //controller ->
        //model.service.parser
        //Собрать данные в БД
        ////

        //model.service.analizer
        //Посчитать показатели на основе собранных данных

        //view
        //Вывести заключение об адекватности вложений в акции выбранной компании
    }
}
