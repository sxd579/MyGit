#coding=utf-8，一定要在最顶行添加
import pymysql


def get_db_uri(dbinfo):
    ENGINE = dbinfo.get('ENGINE') or 'mysql'
    DRIVER = dbinfo.get('DRIVER') or 'pymsql'
    USER = dbinfo.get('USER') or 'root'
    PASSWORD = dbinfo.get('PASSWORD') or '123456'
    HOST = dbinfo.get('HOST') or 'localhost'
    PORT = dbinfo.get('PORT') or '3306'
    DB = dbinfo.get('DB') or 'test'
    return "{}+{}://{}:{}@{}:{}/{}".format(ENGINE, DRIVER, USER, PASSWORD, HOST, PORT, DB)


class Config:
    DEBUG = False
    TESTING = False
    SECRET_KEY = "1234567890qwewqrqwrqwrwqaasdasdasdasdasdsasa"
    SQLALCHEMY_TRACK_MODIFICATIONS = False


# 开发环境
class DevelopConfig(Config):
    DEBUG = True
    DATABASE = {
        "ENGINE": 'mysql',
        "DRIVER": 'pymysql',
        'USER': 'root',
        'PASSWORD': '123456',
        'HOST': 'localhost',
        'PORT': '3306',
        'DB': 'Questions'
    }

    SQLALCHEMY_DATABASE_URI = get_db_uri(DATABASE)


# 测试环境
class TestingConfig(Config):
    TESTING = True
    DATABASE = {
        "ENGINE": 'mysql',
        "DRIVER": 'pymysql',
        'USER': 'root',
        'PASSWORD': '123456',
        'HOST': 'localhost',
        'PORT': '3306',
        'DB': 'FlaskProjcetTesting'
    }

    SQLALCHEMY_DATABASE_URI = get_db_uri(DATABASE)


# 产品环境
class StagingConfig(Config):
    DATABASE = {
        "ENGINE": 'mysql',
        "DRIVER": 'pymysql',
        'USER': 'root',
        'PASSWORD': '123456',
        'HOST': 'localhost',
        'PORT': '3306',
        'DB': 'FlaskProjcetStaging'
    }

    SQLALCHEMY_DATABASE_URI = get_db_uri(DATABASE)


class ProductConfig(Config):
    DATABASE = {
        "ENGINE": 'mysql',
        "DRIVER": 'pymysql',
        'USER': 'root',
        'PASSWORD': '123456',
        'HOST': 'localhost',
        'PORT': '3306',
        'DB': 'FlaskProjcetProduct'
    }

    SQLALCHEMY_DATABASE_URI = get_db_uri(DATABASE)


envs = {
    'develop': DevelopConfig,
    'testing': TestingConfig,
    'staging': StagingConfig,
    'product': ProductConfig
}
