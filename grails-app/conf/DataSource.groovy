dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "com.mysql.cj.jdbc.Driver"
    username = "root"
    password = "password"
}
hibernate {
    dialect = "org.hibernate.dialect.MySQLDialect"
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory' // Hibernate 4
    singleSession = true
    flush.mode = 'manual'
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://127.0.0.1:3307/racetrack_dev?useUnicode=yes&characterEncoding=UTF-8"
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:mysql://127.0.0.1:3307/racetrack_dev?useUnicode=yes&characterEncoding=UTF-8"
        }
    }
    production {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:mysql://127.0.0.1:3307/racetrack_dev?useUnicode=yes&characterEncoding=UTF-8"
        }
    }
}

