#必要なコメントがあるかどうか解析するツール

コーディング基準をもとに，必要なコメントをXMLやJSONといったドメイン固有言語で定義する．
定義に基づき，ソースコード上に必要なコメントが書かれているか解析する．
必要なコメントが書かれていない場合は，定義から書くべきであるコメントの内容を提案する．
コメントは，日本語をメインに対応する．

#UserStory
必要なコメントを定義する　→　必要なコメントを定義したファイルをソフトウェアに読み込ませる　
→　ソースコードを読みこませる　→　解析結果を見て，コメントを追加する　→　ファイルを保存する．

#MVP
ソースコードを読み込む →   予め定義された必要なコメントが有るかどうか解析できる　 → ファイルを保存することができる．


#開発環境
Eclipse + JavaCCプラグイン + JavaFxプラグイン

#JavaFxのプラグインの導入
Eclipseで実行する場合は，javaFx用のプラグインをインストールする必要がある．
http://badbivouac.blog.fc2.com/blog-entry-7.html
を参考にする．

#JavaCCのプラグインの導入
http://qiita.com/again22/items/dccc86bddc5414f8bea8
を参考にする．

#使い方
リポジトリをcloneし，Eclipseにプロジェクトをインポートする．
Eclipseから，File -> Import ->General -> Existing Projects into Workspace　をクリック
MakeCleanCoder.javaを選択して実行する．





















































































