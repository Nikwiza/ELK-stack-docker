from dataclasses import dataclass
from enum import Enum, auto
from typing import List

import random
from argparse import ArgumentParser

import pandas as pd
from faker import Faker


fake = Faker()


class UserClass(Enum):
    Finance = auto()
    Deen = auto()
    Teacher = auto()
    Student = auto()
    Other = auto()


def select_user_class_category() -> UserClass:
    """Select a UserClass category based on predefined probabilities."""
    categories = [
        UserClass.Finance,
        UserClass.Deen,
        UserClass.Teacher,
        UserClass.Student,
        UserClass.Other,
    ]
    probabilities = [0.1, 0.1, 0.2, 0.6, 0]  # Change probs here
    return random.choices(categories, probabilities)[0]


@dataclass
class User:
    user_id: str
    name: str
    lastName: str
    possition: UserClass

    @classmethod
    def generate_fake_user(cls):
        return cls(
            user_id=fake.uuid4(),
            name=fake.first_name(),
            lastName=fake.last_name(),
            possition=select_user_class_category(),
        )


@dataclass
class ExtraFormSubmission:
    transaction_id: str
    user_id: str
    amount: float
    comment: str

    @classmethod
    def generate_fake_transaction(cls, usr: str):
        return cls(
            transaction_id=fake.uuid4(),
            user_id=usr,
            amount=fake.pyfloat(min_value=0, max_value=1000, right_digits=2),
            comment=fake.sentence(nb_words=random.randint(20, 100)),
        )


def generate_fake_transactions(n: int, user: pd.DataFrame):
    tr = []
    filtered_usr = user[
        user["possition"].isin(
            [UserClass.Finance, UserClass.Teacher, UserClass.Finance]
        )
    ]
    if filtered_usr.empty:
        raise ValueError(
            "There is no Teachers, Deens or Finance staff for transaction!"
        )
    for i in range(n):
        usr = filtered_usr['user_id'].sample(1).iloc[0]
        tr.append(ExtraFormSubmission.generate_fake_transaction(usr=usr))
    return pd.DataFrame(tr)


def generate_users(nusers: int):
    users = []
    for i in range(nusers):
        users.append(User.generate_fake_user())
    return pd.DataFrame(users)


if __name__ == "__main__":
    '''

    A script that generates synthetic datasets with the fake library.
    Example:
    python data_set_generator.py -u 300 -n 1000 --path ../dataset

    --ntransactions or -n  -> number of transactions
    --users or -u -> number of users
    --user_file -> path to the users dataset (required if users is not set)

    '''
    parser = ArgumentParser()

    parser.add_argument(
        "-u",
        "--users",
        type=int,
        help="Number of users to generate as an integer. If you do not wish to generate users leave this flag out.",
        default=0,
    )
    parser.add_argument(
        "-n",
        "--ntransactions",
        type=int,
        help="Number of transactions to generate as an integer. If you do not wish to generate transactions leave this flag out.",
        default=0,
    )

    parser.add_argument(
        "--user_file",
        type=str,
        help="Path to users csv, if you are not generating one.",
        required=False,
    )

    parser.add_argument(
        "--path",
        type=str,
        help="Folder in which to store the dataset.",
        default="./",
    )

    args = parser.parse_args()

    assert (
        args.users or args.user_file
    ), "You have to provide a users csv file in order to do anything with the script!"

    if args.users:
        users = generate_users(args.users)
        users.to_csv(args.path + '/users.csv', index=False)
    else:
        users = pd.read_csv(args.user_file)

    if args.ntransactions:
        transactions = generate_fake_transactions(n=args.ntransactions, user=users)
        transactions.to_csv(args.path + '/transactions.csv', index=False)
    
    print('Success! :)')
