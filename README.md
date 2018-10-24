# hugecsv

version Required
java 8
Spark 2.3
maven 3

Directory Hierarchy:
/core
    /InjectorFactory      Guice's context
    /MyConfigModule       Guice Configuration Class
/entity
    /FilePayLoad          file.csv -> List<filePayload>
/service
    IMailSendService      Interface define sending mail
    IFileConvertService   Interface define File mail
    /impl
        FileConvertServiceCsvImpl
        GoogleMailSender   Using Goolge's Server send mail (need to authorization)
        MockMailSender     fake class just simulate mail send
/spark
    SendMailByHugeFileSpark      Main Class
    /injector              in order to get Guice's context in spark jobs,Guice.Injector should be Singleton better
    /stage                 different spark jobs


question
1.Get huge file information with out memory overflow
2.File.csv  -> convert  ->  List<FilePayload>   -> sendmail

personal statement:
Q1:
Spark cluster is better one
Q2:
Using inject ways is easy to solve and extend
if you need parse json data or other types
or you want using your local server to send mail
just bound interface in Guice's Module or Spring's Configuration is Ok