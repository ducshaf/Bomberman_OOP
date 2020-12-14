# Bài tập lớn OOP - Bomberman Game 
ENGLISH CAPTION BELOW!


ENTITY
- Animated Entity
   + Enemy có 4 loại:
      Ghost có thể đi xuyên tường, AI randomDirection, có khả năng dịch chuyển sang 1 tile bất kì trong bản đồ.
      Evil Bomb sử dụng hàm A* (tính cả Destroyable Wall) để tính đường ngắn nhất đên player, khi gặp DestoyableWall sẽ đặt bom, dùng hàm findSafePlace (dijkstra's algorithm) để         tìm tile gần nhất an toàn, dùng A* để tìm đường đến đó. Nếu Evil bomb đến cột hoặc dòng cùng player thì tự nổ tung (fierce bomb).
      Slime di chuyển bằng A*(k tính DestroyableWall) để tìm đường đến player, nếu k thể đến được thì di chuyển bằng randomDirection.
      Snow di chuyển ngẫu nhiên bằng randomDirection.
   + Bomber
   + Bomb & Explosion
      Ấn H để đặt bom.
      Trong bomb có các hàm để di chuyển khi sử dụng powerup The force, nổ hẹn giờ khi dùng TimeBomb, nổ vượt tất cả DestroyableWall khi dùng FierceBomb.
      Bom nổ sau 2.5s, k có animation cho Bomb và Explosion.
      
    Va chạm dùng canMove, và dùng 2 mảng entities, 1 mảng entities để Grass, DestroyableWall, Wall, 1 mảng mobileEntities để AnimatedEntity
- StatusEffect
   + Agile: tăng tốc độ di chuyển của player, mặc định 0.8 (hiển thị 8) mỗi lần ăn tăng 0.1.
   + Blind: Tạo 1 overlay đen để làm mù, vẫn gặp bug khi gameover lúc có debuff này.
   + FierceBomb: bomb nổ vượt qua DestroyableWall, bomblevel = 15.
   + FreezeTime: dừng thời gian, player vẫn có thể di chuyển.
   + Heal: tăng máu nếu máu thấp hơn 3.
   + IncrementBombLevel: tăng phạm vi nổ bom.
   + IncrementBombNumber: tăng số bom có thể đặt.
   + Inversion: đảo chiều điều khiển của người chơi.
   + Percolate: lưu vị trí hiện tại của người chơi. Nếu đến cuối thời gian người chơi vẫn trong tường thì quay lại vị trí đã lưu.
   + Random: 1 status effect random.
   + Slow: làm chậm tốc độ của player xuống 0.5.
   + TimeBomb: bom hẹn giờ, ấn K để nổ.
   
MAP
- hàm generateMap để tạo 1 map random, đặt vị trí của player, Wall và random Enemy.
- hàm inputMap để lấy map từ file Level1.txt, chủ yếu dùng để debug.

GRAPHICS lấy từ openart, dùng Tiled để tạo ground map, overlay, rồi chuyển qua png. Sprite từ openart, tự vẽ.

GUI
Pause tạm dừng trò chơi, ấn Resume hoặc ngoài để tiếp tục
Main Menu chuyển về scene App
Restart khởi động lại màn

AUDIO bgm là うちのまじっく từ Dova-syndrome(royalty free, tks Hololive), sfx từ nhiều nguồn.

1 số bug vẫn hiện hữu như có khả năng (rất nhỏ) Snow đi xuyên tường, khi bị Blind hay đang có FreezeTime mà game over hoặc win thì k xóa đc cái overlay đó.


[ENGLISH]
This is my little project for OOP class in UET-VNU.I'm heavily influenced (inheritance, polymorphism and some methods) by carlosflorencio and ashish2199's projects
This project still has a lot of shortcomings so advice are welcome :D. If this violate any copyright or you just don't like it, contact me through htduc1n@gmail.com
then I will private this.

Some main features:
- Working Bomberman game with pause, resume, restart, bgm, sfx can be muted.
- Generate map by randomly generating enemies then fill the rest with wall and destroyable wall, if you want to use text file instead, in init() method in GameManagement,
replace with MapGenerator.inputMap(); edit map in Level1.txt
- 4 different types of enemy with AI, used A* and dijkstra to find path and find node in map.
- 12 status effect.

Assets:
- Most assets in this can be found on opengameart.org, bgm is from dova-syndrome, the rest are from multiple sources online or drawn by me.
   Bomberman and wall sprites by drummyfish.
   Enemy sprites by Elthen.
   BGM is うちのまじっく by ハヤシユウ.
Kudos to them for their amazing works.

Future development(?):
I might work some more on this when I have time. For example improving collision by using 2D rectangle as bounding box, create more enemy, add more map, support LAN,
fixing some bugs,...
-> Any advice would be appreciated!





