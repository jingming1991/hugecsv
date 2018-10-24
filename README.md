# hugecsv

version Required<br/>
java 8<br/>
Spark 2.3<br/>
maven 3<br/>

Directory Hierarchy:<br/>core<br/>
&nbsp;&nbsp;/InjectorFactory &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Guice's context<br/>
&nbsp;&nbsp;/MyConfigModule &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;       Guice Configuration Class<br/>
/entity<br/>
&nbsp;&nbsp;/FilePayLoad &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;          file.csv -> List<filePayload><br/>
/service<br/>
&nbsp;&nbsp;IMailSendService &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Interface define sending mail<br/>
&nbsp;&nbsp;IFileConvertService &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   Interface define File mail<br/>
&nbsp;&nbsp;/impl<br/>
&nbsp;&nbsp;&nbsp;&nbsp;FileConvertServiceCsvImpl<br/>
&nbsp;&nbsp;&nbsp;&nbsp;GoogleMailSender &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   Using Goolge's Server send mail (need to authorization)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;MockMailSender &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     fake class just simulate mail send<br/>
/spark<br/>
&nbsp;&nbsp;SendMailByHugeFileSpark &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Main Class<br/>
&nbsp;&nbsp;/injector &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;in order to get Guice's context in spark jobs,Guice.Injector should be Singleton better<br/>
&nbsp;&nbsp;/stage &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;different spark jobs<br/>

question<br/>
1.Get huge file information with out memory overflow<br/>
2.File.csv  -> convert  ->  List<FilePayload>   -> sendmail<br/>

personal statement:<br/>
Q1:<br/>
Spark cluster is better one<br/>
Q2:<br/>
Using inject ways is easy to solve and extend<br/>
if you need parse json data or other types<br/>
or you want using your local server to send mail<br/>
just bound interface in Guice's Module or Spring's Configuration is Ok<br/>
