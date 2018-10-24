# hugecsv

version Required<br/>
java 8<br/>
Spark 2.3<br/>
maven 3<br/>

Directory Hierarchy:<br/>
/core<br/>
    /InjectorFactory      Guice's context<br/>
    /MyConfigModule       Guice Configuration Class<br/>
/entity<br/>
    /FilePayLoad          file.csv -> List<filePayload><br/>
/service<br/>
    IMailSendService      Interface define sending mail<br/>
    IFileConvertService   Interface define File mail<br/>
    /impl<br/>
        FileConvertServiceCsvImpl<br/>
        GoogleMailSender   Using Goolge's Server send mail (need to authorization)<br/>
        MockMailSender     fake class just simulate mail send<br/>
/spark<br/>
    SendMailByHugeFileSpark      Main Class<br/>
    /injector              in order to get Guice's context in spark jobs,Guice.Injector should be Singleton better<br/>
    /stage                 different spark jobs<br/>

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