Feature: gittigidiyor Task
  @wip
  Scenario: gittigidiyor Task Steps
    Given www.gittigidiyor.com sitesi açılır.
    When  Arama kutucuğuna "bilgisayar" kelimesi girilir.
    And   Arama sonuçları sayfasından 2.sayfa açılır
    Then  2.sayfanın açıldığı kontrol edilir
    And   Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
    And   Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.
    And   Seçilen ürün sepete eklenir.
    And   Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
    And   Adet arttırılarak ürün adedinin 2 olduğu doğrulanır
    And   Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.
