# 概要

保護ねこと里親をつなぐプログラムを作成します。
データベースの情報にUPDATE機能を付設し、Validationを行うようにしました。

## 起動手順

catsテーブルのレコード初期値は以下のとおりです。

```mysql
mysql> select * from cats;
+----+--------+--------+-----+
| id | name   | sex    | age |
+----+--------+--------+-----+
|  1 | Omochi | female |  10 |
|  2 | Coa    | male   |   3 |
|  3 | Gonchi | male   |   5 |
+----+--------+--------+-----+
3 rows in set (0.01 sec)


```

## UPDATE機能

初期値
![スクリーンショット 2024-07-23 14 14 23](https://github.com/user-attachments/assets/4087d276-e2c1-4bde-9682-4089e9cd145b)


UPDATE成功！
![スクリーンショット 2024-07-23 14 16 58](https://github.com/user-attachments/assets/2db08a46-b1a0-4c19-b04e-26e9ace3d787)


結果
![スクリーンショット 2024-07-23 14 17 30](https://github.com/user-attachments/assets/98240199-de7a-4fcc-b0bf-fc850097034b)


## UPDATE機能のValidation

保護ねこの名前、性別、年齢は必須項目としました。
![スクリーンショット 2024-07-23 14 19 15](https://github.com/user-attachments/assets/e3f9b3ab-c0cf-4507-99e6-4c98bba827dc)

完了
