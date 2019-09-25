# coding=utf-8，一定要在最顶行添加
from flask_migrate import Migrate
from flask_sqlalchemy import SQLAlchemy
from flask_bootstrap import Bootstrap

db = SQLAlchemy()
migrate = Migrate()
bootstrap = Bootstrap()


def init_ext(app):
    db.init_app(app=app)
    migrate.init_app(app=app, db=db)
    bootstrap.init_app(app=app)
