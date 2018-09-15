// 排除compos和visit表
grails.plugin.reveng.excludeTables = ['compos', 'visit']

// 排除以temp开头的表
grails.plugin.reveng.excludeTableRegexes = ['temp.+']

//排除以audit开头的表
grails.plugin.reveng.excludeTableAntPatterns = ['audit_*']

// 指定要生成domain的表
//grails.plugin.reveng.includeTables = ['person', 'organization']
grails.plugin.reveng.includeTables = ['thing']

// 指定域类生成包名
grails.plugin.reveng.packageName = 'com.test'

// 指定乐观锁字段列
grails.plugin.reveng.versionColumns = [other: 'nonstandard_version_name']

// 指定域类对应关系
grails.plugin.reveng.manyToManyTables = ['user_role']
grails.plugin.reveng.manyToManyBelongsTos = ['user_role': 'role', 'author_books': 'book']