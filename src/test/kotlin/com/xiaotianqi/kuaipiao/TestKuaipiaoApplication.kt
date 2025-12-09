package com.xiaotianqi.kuaipiao

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<KuaipiaoApplication>().with(TestcontainersConfiguration::class).run(*args)
}
