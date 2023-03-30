import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.when;
public class DeckTests {
    Deck deck = org.mockito.Mockito.mock(Deck.class);

    String url = "https://deckofcardsapi.com/api/deck";
    @Test
    public void shuffleTest(){
        when(deck.shuffle(url+"/new/shuffle/?deck_count=1")).thenReturn("{\n" +
                "    \"success\": true,\n" +
                "    \"deck_id\": \"3p40paa87x90\",\n" +
                "    \"shuffled\": true,\n" +
                "    \"remaining\": 52\n" +
                "}");
        String shuffle = deck.shuffle(url+"/new/shuffle/?deck_count=1");
        Assert.assertEquals(shuffle,"{\n" +
                "    \"success\": true,\n" +
                "    \"deck_id\": \"3p40paa87x90\",\n" +
                "    \"shuffled\": true,\n" +
                "    \"remaining\": 52\n" +
                "}");
    }
    @Test
    public void drawTest(){
        when(deck.draw(url+"/<<deck_id>>/draw/?count=2")).thenReturn("{\n" +
                "    \"success\": true, \n" +
                "    \"deck_id\": \"kxozasf3edqu\", \n" +
                "    \"cards\": [\n" +
                "        {\n" +
                "            \"code\": \"6H\", \n" +
                "            \"image\": \"https://deckofcardsapi.com/static/img/6H.png\", \n" +
                "            \"images\": {\n" +
                "                          \"svg\": \"https://deckofcardsapi.com/static/img/6H.svg\", \n" +
                "                          \"png\": \"https://deckofcardsapi.com/static/img/6H.png\"\n" +
                "                      }, \n" +
                "            \"value\": \"6\", \n" +
                "            \"suit\": \"HEARTS\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"code\": \"5S\", \n" +
                "            \"image\": \"https://deckofcardsapi.com/static/img/5S.png\", \n" +
                "            \"images\": {\n" +
                "                          \"svg\": \"https://deckofcardsapi.com/static/img/5S.svg\", \n" +
                "                          \"png\": \"https://deckofcardsapi.com/static/img/5S.png\"\n" +
                "                      }, \n" +
                "            \"value\": \"5\", \n" +
                "            \"suit\": \"SPADES\"\n" +
                "        }\n" +
                "    ], \n" +
                "    \"remaining\": 50\n" +
                "}");
        String draw = deck.draw(url+"/<<deck_id>>/draw/?count=2");
        Assert.assertEquals(draw,"{\n" +
                "    \"success\": true, \n" +
                "    \"deck_id\": \"kxozasf3edqu\", \n" +
                "    \"cards\": [\n" +
                "        {\n" +
                "            \"code\": \"6H\", \n" +
                "            \"image\": \"https://deckofcardsapi.com/static/img/6H.png\", \n" +
                "            \"images\": {\n" +
                "                          \"svg\": \"https://deckofcardsapi.com/static/img/6H.svg\", \n" +
                "                          \"png\": \"https://deckofcardsapi.com/static/img/6H.png\"\n" +
                "                      }, \n" +
                "            \"value\": \"6\", \n" +
                "            \"suit\": \"HEARTS\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"code\": \"5S\", \n" +
                "            \"image\": \"https://deckofcardsapi.com/static/img/5S.png\", \n" +
                "            \"images\": {\n" +
                "                          \"svg\": \"https://deckofcardsapi.com/static/img/5S.svg\", \n" +
                "                          \"png\": \"https://deckofcardsapi.com/static/img/5S.png\"\n" +
                "                      }, \n" +
                "            \"value\": \"5\", \n" +
                "            \"suit\": \"SPADES\"\n" +
                "        }\n" +
                "    ], \n" +
                "    \"remaining\": 50\n" +
                "}");
    }
    @Test
    public void reshuffleTest(){
        when(deck.reshuffle(url+"/<<deck_id>>/shuffle/")).thenReturn("{\n" +
                "    \"success\": true,\n" +
                "    \"deck_id\": \"3p40paa87x90\",\n" +
                "    \"shuffled\": true,\n" +
                "    \"remaining\": 52\n" +
                "}");
        String reshuffle = deck.reshuffle(url+"/<<deck_id>>/shuffle/");
        Assert.assertEquals(reshuffle,"{\n" +
                "    \"success\": true,\n" +
                "    \"deck_id\": \"3p40paa87x90\",\n" +
                "    \"shuffled\": true,\n" +
                "    \"remaining\": 52\n" +
                "}");
    }
    @Test
    public void aNewDeckTest(){
        when(deck.aNewDeck(url+"/new/")).thenReturn("{\n" +
                "    \"success\": true,\n" +
                "    \"deck_id\": \"3p40paa87x90\",\n" +
                "    \"shuffled\": false,\n" +
                "    \"remaining\": 52\n" +
                "}");
        String aNewDeck = deck.aNewDeck(url+"/new/");
        Assert.assertEquals(aNewDeck,"{\n" +
                "    \"success\": true,\n" +
                "    \"deck_id\": \"3p40paa87x90\",\n" +
                "    \"shuffled\": false,\n" +
                "    \"remaining\": 52\n" +
                "}");
    }
    @Test
    public void aPartialDeckTest(){
        when(deck.aPartialDeck(url+"/new/shuffle/?cards=AS,2S,KS,AD,2D,KD,AC,2C,KC,AH,2H,KH")).thenReturn("{\n" +
                "    \"success\": true,\n" +
                "    \"deck_id\": \"3p40paa87x90\",\n" +
                "    \"shuffled\": true,\n" +
                "    \"remaining\": 12\n" +
                "}");
        String aPartialDeck = deck.aPartialDeck(url+"/new/shuffle/?cards=AS,2S,KS,AD,2D,KD,AC,2C,KC,AH,2H,KH");
        Assert.assertEquals(aPartialDeck,"{\n" +
                "    \"success\": true,\n" +
                "    \"deck_id\": \"3p40paa87x90\",\n" +
                "    \"shuffled\": true,\n" +
                "    \"remaining\": 12\n" +
                "}");
    }

}